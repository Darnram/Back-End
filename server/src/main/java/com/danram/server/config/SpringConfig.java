package com.danram.server.config;

import com.danram.server.repository.DateLogRepository;
import com.danram.server.repository.MemberRepository;
import com.danram.server.repository.TokenRepository;
import com.danram.server.service.member.MemberService;
import com.danram.server.service.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final DateLogRepository dateLogRepository;
    private final TokenRepository tokenRepository;
    private final ModelMapper modelMapper;

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository, dateLogRepository, tokenRepository, modelMapper);
    }
}
