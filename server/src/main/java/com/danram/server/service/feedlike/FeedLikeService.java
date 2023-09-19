package com.danram.server.service.feedlike;

import com.danram.server.dto.request.feedlike.FeedLikeAddRequestDto;

public interface FeedLikeService {
    Boolean addLike(FeedLikeAddRequestDto dto);
    Boolean removeLike(FeedLikeAddRequestDto dto);
}
