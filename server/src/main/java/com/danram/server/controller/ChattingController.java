package com.danram.server.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatting")
@Api(tags = {"채팅 API"})
@RequiredArgsConstructor
@Slf4j
public class ChattingController {
    /**
     * TODO
     * 채팅 기록
     * 채팅 목록
     * 채팅방 친구 목록
     * 채팅 알람
     * 채팅방 검색
     * */
}
