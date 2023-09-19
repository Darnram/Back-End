package com.danram.server.service.feed;

import com.danram.server.domain.*;

import static com.danram.server.config.MapperConfig.modelMapper;

import com.danram.server.dto.request.feed.FeedAddRequestDto;
import com.danram.server.dto.request.feed.FeedEditRequestDto;
import com.danram.server.dto.response.feed.FeedAddResponseDto;
import com.danram.server.dto.response.feed.FeedEditResponseDto;
import com.danram.server.dto.response.feed.FeedResponseDto;
import com.danram.server.exception.feed.FeedNotFoundException;
import com.danram.server.exception.member.MemberIdNotFoundException;
import com.danram.server.exception.party.PartyNotFoundException;
import com.danram.server.repository.FeedRepository;
import com.danram.server.repository.MemberLikeRepository;
import com.danram.server.repository.MemberRepository;
import com.danram.server.repository.PartyRepository;
import com.danram.server.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final MemberLikeRepository memberLikeRepository;
    private final PartyRepository partyRepository;
    private final MemberRepository memberRepository;

    public FeedServiceImpl(final FeedRepository feedRepository,
                           final MemberLikeRepository memberLikeRepository,
                           final PartyRepository partyRepository,
                           final MemberRepository memberRepository) {
        this.feedRepository = feedRepository;
        this.memberLikeRepository = memberLikeRepository;
        this.memberRepository = memberRepository;
        this.partyRepository = partyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedResponseDto> findFeed(Integer pages, Long partyId) {
        Long memberId = JwtUtil.getMemberId();
        List<FeedResponseDto> responseDtoList = new ArrayList<>();

        Sort sort = Sort.by(Sort.Direction.DESC,"createdAt");
        Pageable pageable = PageRequest.of(pages,10,sort);
        Slice<Feed> feedList = feedRepository.findByPartyId(partyId,pageable);

        for (Feed feed : feedList) {
            FeedResponseDto responseDto = modelMapper.map(feed, FeedResponseDto.class);

            Optional<MemberLike> memberLike = memberLikeRepository.findActiveMemberLike(memberId,feed.getFeedId(),0L);
            responseDto.setIsLiked(memberLike.isPresent());

            responseDto.setMemberInfo(feed.getMember());
            responseDto.setHasNextSlice(feedList.hasNext());
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    @Override
    @Transactional
    public FeedAddResponseDto addFeed(FeedAddRequestDto dto, String imgUrl) {
        Long memberId = JwtUtil.getMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberIdNotFoundException(memberId.toString()));

        DateLog dateLog = DateLog.builder()
                .memberId(memberId)
                .description(String.format("유저 Id %d가 모임 id %d에 피드를 생성",memberId,dto.getPartyId()))
                .build();

        Party party = partyRepository.findById(dto.getPartyId())
                .orElseThrow(() -> new PartyNotFoundException(dto.getPartyId().toString()));

        Feed feed = modelMapper.map(dto,Feed.class);
        feed.setParty(party);
        feed.setMember(member);
        feed.setDateLog(dateLog);
        feed.setImg(imgUrl);
        feed.setCommentCount(0L);
        feed.setLikeCount(0L);

        feedRepository.save(feed);

        return modelMapper.map(feed, FeedAddResponseDto.class);
    }

    @Override
    @Transactional
    public FeedEditResponseDto editFeed(FeedEditRequestDto dto, String imgUrl) {
        Long memberId = JwtUtil.getMemberId();

        Feed feed = feedRepository.findByFeedIdAndMemberId(dto.getFeedId(), memberId)
                .orElseThrow(() -> new FeedNotFoundException(dto.getFeedId().toString()));
        feed.updateFeed(dto,imgUrl);

        feedRepository.save(feed);

        return modelMapper.map(feed, FeedEditResponseDto.class);
    }

    @Override
    @Transactional
    public Boolean deleteFeed(Long feedId) {
        Long memberId = JwtUtil.getMemberId();

        Feed feed = feedRepository.findByMemberId(feedId,memberId)
                .orElseThrow(() -> new FeedNotFoundException(feedId.toString()));

        feed.setDeletedAt(LocalDate.now());

        return true;
    }
}
