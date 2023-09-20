package com.danram.server.service.commentlike;

import com.danram.server.domain.Comment;
import com.danram.server.domain.DateLog;
import com.danram.server.domain.Member;
import com.danram.server.domain.MemberLike;
import com.danram.server.dto.request.comment.CommentLikeRequestDto;
import com.danram.server.exception.comment.CommentNotFoundException;
import com.danram.server.exception.member.MemberIdNotFoundException;
import com.danram.server.exception.memberlike.MemberLikeNotFoundException;
import com.danram.server.repository.CommentRepository;
import com.danram.server.repository.MemberLikeRepository;
import com.danram.server.repository.MemberRepository;
import com.danram.server.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
public class CommentLikeServiceImpl implements CommentLikeService {
    private final CommentRepository commentRepository;
    private final MemberLikeRepository memberLikeRepository;
    private final MemberRepository memberRepository;

    public CommentLikeServiceImpl(final CommentRepository commentRepository,
                                  final MemberLikeRepository memberLikeRepository,
                                  final MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.memberLikeRepository = memberLikeRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Boolean addCommentLike(CommentLikeRequestDto dto) {
        Long memberId = JwtUtil.getMemberId();

        Comment comment = commentRepository.findMyComment(memberId,dto.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(dto.getCommentId().toString()));

        Optional<MemberLike> memberLike = memberLikeRepository.findActiveMemberLike(memberId,dto.getCommentId(),1L);

        if (memberLike.isPresent()) { // 좋아요 존재. 좋아요 설정
            memberLike.get().setDeletedAt(null);
        } else { // 새로운 좋아요 추가
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new MemberIdNotFoundException(memberId.toString()));

            DateLog dateLog = DateLog.builder()
                    .memberId(memberId)
                    .description(String.format("member id %d가 댓글에 좋아요를 추가함.",memberId))
                    .build();

            MemberLike like = MemberLike.builder()
                    .type(1L)
                    .id(dto.getCommentId())
                    .member(member)
                    .dateLog(dateLog)
                    .build();

            memberLikeRepository.save(like);
        }

        comment.plusLikeCount();

        return true;
    }

    @Override
    @Transactional
    public Boolean removeCommentLike(CommentLikeRequestDto dto) {
        Long memberId = JwtUtil.getMemberId();

        Comment comment = commentRepository.findMyComment(memberId,dto.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(dto.getCommentId().toString()));

        MemberLike memberLike = memberLikeRepository.findActiveMemberLike(memberId,dto.getCommentId(),1L)
                .orElseThrow(() -> new MemberLikeNotFoundException(0L,dto.getCommentId()));

        memberLike.setDeletedAt(LocalDate.now());

        comment.minusLikeCount();
        
        return true;
    }
}
