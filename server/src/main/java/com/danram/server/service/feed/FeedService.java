package com.danram.server.service.feed;

import com.danram.server.dto.request.feed.FeedAddRequestDto;
import com.danram.server.dto.response.feed.FeedAddResponseDto;

import java.util.List;

public interface FeedService {
    public FeedAddResponseDto addFeed(FeedAddRequestDto dto, List<String> files);
}
