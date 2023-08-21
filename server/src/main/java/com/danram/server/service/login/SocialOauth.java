package com.danram.server.service.login;

import com.danram.server.dto.response.login.OauthLoginResponseDto;

public interface SocialOauth {
    String getOauthRedirectURL();
    String getAccessToken(String code) ;
    OauthLoginResponseDto getLoginResponseDto(String idToken);
    default SocialLoginType type() {
        if (this instanceof KakaoOauth) {
            return SocialLoginType.kakao;
        } else if (this instanceof NaverOauth) {
            return SocialLoginType.naver;
        } else {
            return null;
        }
    }
}
