package com.danram.server.dto.response.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentAddResponseDto {
    private Long commentId;
    private String content;
}
