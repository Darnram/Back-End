package com.danram.server.dto.response.feed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedAddResponseDto {
    /**
     * 확장성을 위해 image list로 실제로는 이미지 하나만
     * */
    private Long feedId;
    private List<String> images;
    private String content;
}
