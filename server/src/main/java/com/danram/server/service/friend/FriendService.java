package com.danram.server.service.friend;

import com.danram.server.dto.request.friend.FriendRequestDto;
import com.danram.server.dto.response.friend.FriendResponseDto;

public interface FriendService {
    public FriendResponseDto getFriends(FriendRequestDto friendRequestDto);
}
