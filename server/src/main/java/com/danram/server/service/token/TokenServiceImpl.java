package com.danram.server.service.token;

import com.danram.server.domain.Member;
import com.danram.server.domain.Token;
import com.danram.server.dto.request.token.TokenRequestDto;
import com.danram.server.dto.response.token.TokenResponseDto;
import com.danram.server.exception.member.MemberIdNotFoundException;
import com.danram.server.exception.token.TokenNotFoundException;
import com.danram.server.repository.MemberRepository;
import com.danram.server.repository.TokenRepository;
import com.danram.server.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import static com.danram.server.config.MapperConfig.modelMapper;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;

    @Override
    public Boolean isExpired() {
        return JwtUtil.isExpired(JwtUtil.getAccessToken());
    }

    @Override
    @Transactional
    public TokenResponseDto reissueAccessToken(Long memberId) {
        String accessToken = JwtUtil.getAccessToken();
        String refreshToken = JwtUtil.getRefreshToken();

        Token token = tokenRepository.findByAccessTokenAndRefreshToken(accessToken,refreshToken)
                .orElseThrow(() -> new TokenNotFoundException());

        Member member = memberRepository.findById(memberId)
                        .orElseThrow(() -> new MemberIdNotFoundException(memberId.toString()));

        token.setAccessToken(JwtUtil.createJwt(member.getMemberId()));
        token.setAccessTokenExpiredAt(LocalDate.now().plusDays(JwtUtil.ACCESS_TOKEN_EXPIRE_TIME));
        tokenRepository.save(token);

        return modelMapper.map(token, TokenResponseDto.class);
    }
}
