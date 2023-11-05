package com.danram.server.dto.request.feed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedAddRequestDto {
    /**
     * 확장성을 위해 Image list로 하고 실제로는 하나만
     * */
    @NotNull
    private Long partyId;
    @Null
    private List<MultipartFile> images;
    @Null
    private String content;
}
