package com.danram.server.service.chatting;

import com.danram.server.vo.UserInfo;

public interface ChattingService {
    public UserInfo getInfo(Long id);
}