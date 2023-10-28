package com.danram.server.service.friend;

import com.danram.server.domain.Friend;
import com.danram.server.domain.Member;
import com.danram.server.dto.request.friend.FriendRequestDto;
import com.danram.server.dto.request.friend.FriendRequestMockDto;
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
    public FriendResponseDto getFriends(FriendRequestDto friendRequestDto) {
        Long memberId = JwtUtil.getMemberId();

        List<Friend> friendList = null;

        if (friendRequestDto.getNickname() == null) {
            friendList = friendRepository.findAllByMember(FriendRequestMockDto.toMemberEntity(memberId));
        }
        else {
            Member memberEntity = FriendRequestMockDto.toMemberEntity(memberId);

            friendList = friendRepository.findAllByMemberAndFriend_NicknameContains(memberEntity, friendRequestDto.getNickname());
        }
        return FriendResponseDto
                .builder()
                .friends(friendList.stream().map(FriendResponseDto.FriendResponseItemDto::of).collect(Collectors.toList()))
                .build();
    }
}
