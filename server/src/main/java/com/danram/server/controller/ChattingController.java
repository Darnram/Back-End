package com.danram.server.controller;

import com.danram.server.service.chatting.RedisMessagePubService;
import com.danram.server.vo.ChattingMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
    private final RedisMessagePubService redisMessagePubService;

    //Client 가 SEND 할 수 있는 경로
    //stompConfig 에서 설정한 applicationDestinationPrefixes 와 @MessageMapping 경로가 병합됨
    //"/pub/chat/chatting"
    @MessageMapping("/chatting-service")
    public void getChattingMessage(ChattingMessage chattingMessage) {
        redisMessagePubService.publish(chattingMessage);
    }
}
