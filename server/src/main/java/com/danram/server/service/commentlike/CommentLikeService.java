package com.danram.server.service.commentlike;

import com.danram.server.dto.request.comment.CommentLikeRequestDto;

public interface CommentLikeService {
    Boolean addCommentLike(CommentLikeRequestDto dto);
    Boolean removeCommentLike(CommentLikeRequestDto dto);
}
