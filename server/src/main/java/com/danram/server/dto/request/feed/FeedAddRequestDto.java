package com.danram.server.dto.request.feed;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FeedAddRequestDto {
    private Long partyId;
    MultipartFile feedImg;
    private String content;
}
