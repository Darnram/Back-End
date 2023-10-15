package com.danram.server.service.member;

import com.danram.server.domain.Authority;
import com.danram.server.domain.Member;
import com.danram.server.dto.response.login.LoginResponseDto;
import com.danram.server.dto.response.login.OauthLoginResponseDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Optional<Member> checkDuplicatedEmail(String name);
    public LoginResponseDto signUp(OauthLoginResponseDto oauthLoginResponseDto);
    public LoginResponseDto signIn(Member member);
    public List<Authority> getAuthorities();
}
