package com.danram.server.service.feed;

import com.danram.server.dto.request.feed.FeedAddRequestDto;
import com.danram.server.dto.request.feed.FeedEditRequestDto;
import com.danram.server.dto.response.feed.FeedAddResponseDto;
import com.danram.server.dto.response.feed.FeedEditResponseDto;
import com.danram.server.dto.response.feed.FeedResponseDto;

import java.util.List;

public interface FeedService {
    List<FeedResponseDto> findFeed(Integer pages, Long partyId);

    FeedAddResponseDto addFeed(FeedAddRequestDto dto,String imgUrl);

    FeedEditResponseDto editFeed(FeedEditRequestDto dto,String imgUrl);

    Boolean deleteFeed(Long feedId);
}
