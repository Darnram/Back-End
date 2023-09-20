package com.danram.server.service.comment;

import com.danram.server.domain.*;
import com.danram.server.dto.request.comment.CommentAddRequestDto;
import com.danram.server.dto.request.comment.CommentEditRequestDto;
import com.danram.server.dto.response.comment.CommentAddResponseDto;
import com.danram.server.dto.response.comment.CommentEditResponseDto;
import com.danram.server.dto.response.comment.FeedCommentResponseDto;
import com.danram.server.exception.comment.CommentNotFoundException;
import com.danram.server.exception.feed.FeedNotFoundException;
import com.danram.server.exception.member.MemberIdNotFoundException;
import com.danram.server.repository.CommentRepository;
import com.danram.server.repository.FeedRepository;
import com.danram.server.repository.MemberLikeRepository;
import com.danram.server.repository.MemberRepository;
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

import static com.danram.server.config.MapperConfig.modelMapper;

@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final FeedRepository feedRepository;
    private final MemberLikeRepository memberLikeRepository;

    public CommentServiceImpl(final CommentRepository commentRepository,
                              final MemberRepository memberRepository,
                              final FeedRepository feedRepository,
                              final MemberLikeRepository memberLikeRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.feedRepository = feedRepository;
        this.memberLikeRepository = memberLikeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedCommentResponseDto> findParentComment(Integer pages, Long id) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdAt");
        Pageable pageable = PageRequest.of(pages,10,sort);
        Slice<Comment> commentList = commentRepository.findParentComment(id,pageable);

        return covertSliceToCommentDtoList(commentList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedCommentResponseDto> findChildComment(Integer pages, Long commentId) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdAt");
        Pageable pageable = PageRequest.of(pages,10,sort);
        Slice<Comment> commentList = commentRepository.findChildComment(commentId,pageable);

        return covertSliceToCommentDtoList(commentList);
    }

    @Override
    @Transactional
    public CommentAddResponseDto addFeedComment(CommentAddRequestDto dto) {
        Long memberId = JwtUtil.getMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberIdNotFoundException(memberId.toString()));

        DateLog dateLog = DateLog.builder()
                .memberId(memberId)
                .description(String.format("%d id 유저가 %d type의 댓글을 생성함",memberId,0L))
                .build();

        Feed feed = feedRepository.findById(dto.getId())
                .orElseThrow(() -> new FeedNotFoundException(dto.getId().toString()));
        feed.plusCommentCount();

        Comment comment = modelMapper.map(dto,Comment.class);
        comment.setDateLog(dateLog);
        comment.setMember(member);
        comment.setLikeCount(0L);
        comment.setCommentCount(0L);

        if (comment.getParentId()!=null) { // 자식 댓글 추가라면 부모 댓글에 댓글 카운트 증가
            Comment parentComment = commentRepository.findById(comment.getParentId())
                    .orElseThrow(() -> new CommentNotFoundException(comment.getParentId().toString()));
            parentComment.plusCommentCount();
        }

        return modelMapper.map(commentRepository.save(comment), CommentAddResponseDto.class);
    }

    @Override
    @Transactional
    public CommentEditResponseDto editComment(CommentEditRequestDto dto) {
        Long memberId = JwtUtil.getMemberId();

        Comment comment = commentRepository.findMyComment(memberId,dto.getCommentId())
                        .orElseThrow(() -> new CommentNotFoundException(dto.getCommentId().toString()));
        comment.setContent(dto.getContent());

        return modelMapper.map(commentRepository.save(comment), CommentEditResponseDto.class);
    }

    @Override
    @Transactional
    public Boolean deleteFeedComment(Long commentId,Long feedId) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId.toString()));
        comment.setDeletedAt(LocalDate.now());

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new FeedNotFoundException(feedId.toString()));
        feed.minusCommentCount();

        if (comment.getParentId() == null) { // 부모 댓글이라면 자식 댓글이 모두 지워져야 한다.
            List<Comment> commentList = commentRepository.findChildByParentId(comment.getCommentId());

            for (Comment child : commentList) {
                child.setDeletedAt(LocalDate.now());
                feed.minusCommentCount();
            }
        } else { // 자식댓글 이면, 부모 댓글에서 댓글 수 1 감소
            Comment parentComment = commentRepository.findById(comment.getParentId())
                    .orElseThrow(() -> new CommentNotFoundException(commentId.toString()));
            parentComment.minusCommentCount();
        }

        return true;
    }

    private List<FeedCommentResponseDto> covertSliceToCommentDtoList(Slice<Comment> commentSlice) {
        Long memberId = JwtUtil.getMemberId();
        List<FeedCommentResponseDto> responseDtoList = new ArrayList<>();

        for (Comment comment : commentSlice) {
            FeedCommentResponseDto responseDto = modelMapper.map(comment, FeedCommentResponseDto.class);
            Optional<MemberLike> memberLike = memberLikeRepository.findActiveMemberLike(memberId,comment.getCommentId(),1L);
            responseDto.setFeedId(comment.getId());
            responseDto.setCommentId(comment.getCommentId());
            responseDto.setIsLiked(memberLike.isPresent());
            responseDto.setMemberInfo(comment.getMember());
            responseDto.setIsMyComment(comment.getMember().getMemberId().equals(memberId));
            responseDto.setHasNextSlice(commentSlice.hasNext());
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }
}
