package com.danram.server.service.member;

import com.danram.server.domain.Authority;
import com.danram.server.domain.DateLog;
import com.danram.server.domain.Member;
import com.danram.server.domain.Token;
import com.danram.server.dto.response.login.LoginResponseDto;
import com.danram.server.dto.response.login.OauthLoginResponseDto;
import com.danram.server.exception.member.MemberIdNotFoundException;
import com.danram.server.repository.MemberRepository;
import com.danram.server.repository.TokenRepository;
import com.danram.server.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import static com.danram.server.config.MapperConfig.modelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;

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

        DateLog dateLog = DateLog.builder()
                .memberId(id)
                .description(id + "유저가 회원가입을 진행함")
                .build();

        Member user = Member.builder()
                .memberId(id)
                .dateLog(dateLog)
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
        Token token = tokenRepository.findByMemberId(
                member.getMemberId()).orElseThrow(
                        () -> new MemberIdNotFoundException(member.getMemberId().toString())
        );

        token.setAccessToken(JwtUtil.createJwt(member.getMemberId()));
        token.setAccessTokenExpiredAt(LocalDate.now().plusYears(1));
        token.setRefreshToken(JwtUtil.createRefreshToken(member.getMemberId()));
        token.setRefreshTokenExpiredAt(LocalDate.now().plusYears(1));

        return LoginResponseDto.of(member,token, modelMapper);
    }

    @Override
    public List<Authority> getAuthorities() {
        Member member = memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId().toString())
        );

        return member.getAuthorities();
    }
}
