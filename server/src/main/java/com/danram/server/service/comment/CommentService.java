package com.danram.server.service.comment;

import com.danram.server.dto.request.comment.CommentAddRequestDto;
import com.danram.server.dto.request.comment.CommentEditRequestDto;
import com.danram.server.dto.response.comment.CommentAddResponseDto;
import com.danram.server.dto.response.comment.CommentEditResponseDto;
import com.danram.server.dto.response.comment.FeedCommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentAddResponseDto addFeedComment(CommentAddRequestDto dto);

    List<FeedCommentResponseDto> findParentComment(Integer pages, Long id);
    List<FeedCommentResponseDto> findChildComment(Integer pages, Long commentId);

    CommentEditResponseDto editComment(CommentEditRequestDto dto);

    Boolean deleteFeedComment(Long commentId,Long feedId);
}
