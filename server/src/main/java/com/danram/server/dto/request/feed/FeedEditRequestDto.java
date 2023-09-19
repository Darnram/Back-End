package com.danram.server.dto.request.feed;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FeedEditRequestDto {
    private Long feedId;
    private MultipartFile feedImg;
    private String content;
}
