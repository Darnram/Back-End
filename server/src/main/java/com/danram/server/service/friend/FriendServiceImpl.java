package com.danram.server.service.friend;

import com.danram.server.domain.Friend;
import com.danram.server.dto.request.friend.ReqFriendDto;
import com.danram.server.dto.response.friend.FriendResponseDto;
import com.danram.server.repository.FriendRepository;
import com.danram.server.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;

    @Override
    @Transactional(readOnly = true)
    public FriendResponseDto getFriends() {
        Long memberId = JwtUtil.getMemberId();

        List<Friend> friendList = friendRepository.findAllByMember(ReqFriendDto.toMemberEntity(memberId));

        return FriendResponseDto
                .builder()
                .friends(friendList.stream().map(FriendResponseDto.FriendResponseItemDto::of).collect(Collectors.toList()))
                .build();
    }
}
