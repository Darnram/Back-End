package com.danram.server.service.member;

import com.danram.server.domain.Authority;
import com.danram.server.domain.DateLog;
import com.danram.server.domain.Member;
import com.danram.server.domain.Token;
import com.danram.server.dto.response.login.LoginResponseDto;
import com.danram.server.dto.response.login.OauthLoginResponseDto;
import com.danram.server.exception.user.UserIdNotFoundException;
import com.danram.server.repository.DateLogRepository;
import com.danram.server.repository.MemberRepository;
import com.danram.server.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final DateLogRepository dateLogRepository;
    private final TokenRepository tokenRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Optional<Member> checkDuplicatedEmail(final String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public LoginResponseDto signUp(final OauthLoginResponseDto oauthLoginResponseDto) {
        long id = System.currentTimeMillis();

        //권한
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Authority authority1 = Authority.builder()
                .authorityName("ROLE_ADMIN")
                .build();

        //날짜 로그
        DateLog dateLog = DateLog.of(id);

        Member user = Member.builder()
                .memberId(id)
                .logId(dateLogRepository.save(dateLog).getLogId())
                .authorities(Arrays.asList(authority, authority1))
                .email(oauthLoginResponseDto.getEmail())
                .nickname(oauthLoginResponseDto.getNickname())
                .img(oauthLoginResponseDto.getProfileImg())
                .loginType(oauthLoginResponseDto.getLoginType())
                .pro(true)
                .ban(false)
                .build();

        final Member save = memberRepository.save(user);

        Token token = Token.of(user);
        tokenRepository.save(token);

        return LoginResponseDto.of(save,token, modelMapper);
    }

    @Override
    @Transactional
    public LoginResponseDto signIn(final Member member) {
        Token token = tokenRepository.findByMemberId(member.getMemberId()).orElseThrow(() -> new UserIdNotFoundException(member.getMemberId().toString()));

        return LoginResponseDto.of(member,token, modelMapper);
    }
}
