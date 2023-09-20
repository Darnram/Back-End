package com.danram.server.dto.request.comment;

import lombok.Getter;

@Getter
public class CommentAddRequestDto {
    private Long id;
    private Long parentId;
    private String content;
}
