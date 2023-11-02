package com.danram.server.service.chatting;

import com.danram.server.repository.MemberRepository;
import com.danram.server.vo.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

@RequiredArgsConstructor
@Slf4j
public class ChattingServiceImpl implements ChattingService {
    private final MemberRepository userRepository;

    @Cacheable(key = "#id", value = "userInfo")
    public UserInfo getInfo(Long id) {
        /*return userRepository.findName(id).orElseThrow(
                () -> new RuntimeException()
        );*/

        return null;
    }
}
