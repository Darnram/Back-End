package com.danram.server.dto.response.login;

import com.danram.server.domain.Token;
import lombok.Builder;
import lombok.Getter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Getter
@Builder
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private Long id;
    private LocalDate accessTokenExpiredAt;
    private LocalDate refreshTokenExpiredAt;

    public static TokenResponseDto of(Token token, ModelMapper mapper) {
        return mapper.map(token, TokenResponseDto.class);
    }
}
