package com.danram.server.service.member;

import com.danram.server.domain.Authority;
import com.danram.server.domain.Member;
import com.danram.server.dto.request.member.MemberEditRequestDto;
import com.danram.server.dto.response.login.LoginResponseDto;
import com.danram.server.dto.response.login.OauthLoginResponseDto;
import com.danram.server.dto.response.member.MemberInfoResponseDto;
import com.danram.server.dto.response.member.MemberResponseDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Optional<Member> checkDuplicatedEmail(String name);
    public LoginResponseDto signUp(OauthLoginResponseDto oauthLoginResponseDto);
    public LoginResponseDto signIn(Member member);
    public List<Authority> getAuthorities();
    public String getAuthority();
    public MemberResponseDto getInfo();
    public MemberResponseDto findByNickname(String name);

    public List<MemberInfoResponseDto> getAll();
    public MemberInfoResponseDto editInfo(MemberEditRequestDto memberEditRequestDto, String upload);
    public Boolean isDuplicatedEmail(String email);

    public void signOut();
}
