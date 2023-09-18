package com.danram.server.dto.response.token;

import com.danram.server.domain.Token;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Getter
@Setter
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private Long memberId;
    private LocalDate accessTokenExpiredAt;
    private LocalDate refreshTokenExpiredAt;
}
