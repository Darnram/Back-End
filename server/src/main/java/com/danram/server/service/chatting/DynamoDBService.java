package com.danram.server.service.chatting;

import com.danram.server.domain.Chatting;
import com.danram.server.vo.ChattingMessageResponse;

import java.util.List;

public interface DynamoDBService {
    public Boolean createTable();
    public void save(ChattingMessageResponse chatting);
    public List<Chatting> findMessageHistory(Long id);
}
