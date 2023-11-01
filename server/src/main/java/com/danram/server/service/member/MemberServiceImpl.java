package com.danram.server.service.member;

import com.danram.server.domain.Authority;
import com.danram.server.domain.DateLog;
import com.danram.server.domain.Member;
import com.danram.server.domain.Token;
import com.danram.server.dto.request.member.MemberEditRequestDto;
import com.danram.server.dto.response.login.LoginResponseDto;
import com.danram.server.dto.response.login.OauthLoginResponseDto;
import com.danram.server.dto.response.member.MemberInfoResponseDto;
import com.danram.server.dto.response.member.MemberResponseDto;
import com.danram.server.exception.member.MemberIdNotFoundException;
import com.danram.server.exception.member.MemberLoginTypeNotExistException;
import com.danram.server.exception.member.MemberNameNotFoundException;
import com.danram.server.repository.MemberRepository;
import com.danram.server.repository.TokenRepository;
import com.danram.server.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import static com.danram.server.config.MapperConfig.modelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
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
                .signOut(0L)
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
    @Transactional(readOnly = true)
    public List<Authority> getAuthorities() {
        Member member = memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId().toString())
        );

        return member.getAuthorities();
    }

    @Override
    public String getAuthority() {
        Member member = memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId().toString())
        );

        if(member.getAuthorities().size() == 2) {
            return "ROLE_ADMIN";
        }
        else if(member.getAuthorities().size() == 1)
            return "ROLE_USER";
        else
            throw new MemberIdNotFoundException(JwtUtil.getMemberId().toString());
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto getInfo() {
        Member member = memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId().toString())
        );

        return modelMapper.map(member, MemberResponseDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto findByNickname(String name) {
        final Member member = memberRepository.findByNickname(name).orElseThrow(
                () -> new MemberNameNotFoundException(name)
        );

        return modelMapper.map(member, MemberResponseDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberInfoResponseDto> getAll() {
        List<MemberInfoResponseDto> result = new ArrayList<>();

        final List<Member> all = memberRepository.findAll();

        for(Member member : all) {
            MemberInfoResponseDto map = modelMapper.map(member, MemberInfoResponseDto.class);

            map.setLoginType(getLoginType(member));

            map.setCreatedAt(member.getDateLog().getCreatedAt());

            result.add(map);
        }

        return result;
    }

    @Override
    @Transactional
    public MemberInfoResponseDto editInfo(MemberEditRequestDto memberEditRequestDto, String upload) {
        Member member = memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId().toString())
        );

        if(!upload.equals(""))
            member.setImg(upload);

        if(!member.getNickname().equals(memberEditRequestDto.getNickname()) && !memberEditRequestDto.getNickname().trim().isEmpty())
            member.setNickname(memberEditRequestDto.getNickname());
        else
            log.warn("member id: {} input white space name", memberEditRequestDto.getNickname());

        MemberInfoResponseDto map = modelMapper.map(member, MemberInfoResponseDto.class);

        map.setLoginType(getLoginType(member));

        return map;
    }

    @Override
    public Boolean isDuplicatedEmail(String email) {
        return memberRepository.findByEmail(email).isEmpty();
    }

    private String getLoginType(Member member) {
        if(member.getLoginType() == 0L) {
            return "Google";
        } else if(member.getLoginType() == 1L) {
            return "Kakao";
        } else if(member.getLoginType() == 3L) {
            return "apple";
        }
        else
        {
            throw new MemberLoginTypeNotExistException(member.getLoginType());
        }
    }
}
