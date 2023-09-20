package com.danram.server.dto.response.comment;

import com.danram.server.domain.embeddable.CommentPk;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CommentAddResponseDto {
    private CommentPk commentPk;
    private String content;
}
