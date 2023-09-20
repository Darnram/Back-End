package com.danram.server.dto.request.comment;

import lombok.Getter;

@Getter
public class CommentChildAddRequestDto {
    private Long id;
    private Long type;
    private String content;
}
