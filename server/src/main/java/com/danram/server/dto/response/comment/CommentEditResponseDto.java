package com.danram.server.dto.response.comment;

import com.danram.server.domain.embeddable.CommentPk;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter // Getter, Setter 관련해서 컨벤션 정하고 추후에 리펙토링 해야할 것 같습니다.
public class CommentEditResponseDto {
    private String content;
    private LocalDate updatedAt;
}
