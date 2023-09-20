package com.danram.server.service.feedlike;

import com.danram.server.domain.*;
import com.danram.server.dto.request.feedlike.FeedLikeAddRequestDto;
import com.danram.server.exception.feed.FeedNotFoundException;
import com.danram.server.exception.member.MemberIdNotFoundException;
import com.danram.server.exception.memberlike.MemberLikeNotFoundException;
import com.danram.server.repository.MemberLikeRepository;
import com.danram.server.repository.FeedRepository;
import com.danram.server.repository.MemberRepository;
import com.danram.server.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
public class FeedLikeServiceImpl implements FeedLikeService {
    private final FeedRepository feedRepository;
    private final MemberLikeRepository memberLikeRepository;
    private final MemberRepository memberRepository;

    public FeedLikeServiceImpl(final FeedRepository feedRepository,
                               final MemberLikeRepository memberLikeRepository,
                               final MemberRepository memberRepository) {
        this.feedRepository = feedRepository;
        this.memberLikeRepository = memberLikeRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Boolean addLike(FeedLikeAddRequestDto dto) {
        Long memberId = JwtUtil.getMemberId();

        Feed feed = feedRepository.findById(dto.getFeedId())
                .orElseThrow(() -> new FeedNotFoundException(dto.getFeedId().toString()));

        Optional<MemberLike> memberLike = memberLikeRepository.findByMemberIdAndId(memberId,dto.getFeedId(),0L);

        if (memberLike.isPresent()) { // 삭제된 상태의 좋아요가 있을때
            memberLike.get().setDeletedAt(null);
        } else { // 좋아요 새로 추가
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new MemberIdNotFoundException(memberId.toString()));

            DateLog dateLog = DateLog.builder()
                    .memberId(memberId)
                    .description(String.format("member id %d가 피드에 좋아요를 추가함.",memberId))
                    .build();

            MemberLike like = MemberLike.builder()
                    .type(0L)
                    .id(dto.getFeedId())
                    .member(member)
                    .dateLog(dateLog)
                    .build();
            memberLikeRepository.save(like);
        }

        feed.plusLikeCount();

        return true;
    }

    @Override
    @Transactional
    public Boolean removeLike(FeedLikeAddRequestDto dto) {
        Long memberId = JwtUtil.getMemberId();

        Feed feed = feedRepository.findByMemberId(dto.getFeedId(),memberId)
                .orElseThrow(() -> new FeedNotFoundException(dto.getFeedId().toString()));

        MemberLike memberLike = memberLikeRepository.findActiveMemberLike(memberId,dto.getFeedId(),0L)
                .orElseThrow(() -> new MemberLikeNotFoundException(0L,dto.getFeedId()));

        memberLike.setDeletedAt(LocalDate.now());
        feed.minusLikeCount();

        return true;
    }
}
