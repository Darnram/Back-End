package com.danram.server.dto.request.comment;

import lombok.Getter;

@Getter
public class CommentEditRequestDto {
    private Long commentId;
    private String content;
}
