package com.danram.server.controller;

import com.danram.server.dto.request.friend.FriendRequestDto;
import com.danram.server.dto.response.friend.FriendResponseDto;
import com.danram.server.service.friend.FriendService;
import com.danram.server.util.JwtUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friend")
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"친구 API"})
public class FriendController {
    /**
     * TODO
     * 친구 목록
     * 친구 검색
     * 친구 삭제
     * 친구 신청
     * */

    private final FriendService friendService;

    @GetMapping("/my")
    public ResponseEntity<FriendResponseDto> getFriends(@ModelAttribute FriendRequestDto friendRequestDto) {
        return ResponseEntity.ok(friendService.getFriends(friendRequestDto));
    }
}
