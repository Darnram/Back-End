package com.danram.server.service.chatting;

import com.danram.server.vo.ChattingMessage;

public interface RedisMessagePubService {
    public void publish(ChattingMessage chattingMessage);
}