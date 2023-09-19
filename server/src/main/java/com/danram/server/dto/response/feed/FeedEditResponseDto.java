package com.danram.server.dto.response.feed;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FeedEditResponseDto {
    private Long feedId;
    private String img;
    private String content;
    private LocalDate updatedAt;
}
