package com.danram.server.service.token;

import com.danram.server.dto.request.token.TokenRequestDto;
import com.danram.server.dto.response.token.TokenResponseDto;

public interface TokenService {
    Boolean  isExpired();
    TokenResponseDto reissueAccessToken(Long memberId);
}
