package com.danram.server.service.login;

import com.danram.server.dto.response.login.OauthLoginResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;

    public void request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();

        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public OauthLoginResponseDto getLoginResponseDto(SocialLoginType socialLoginType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);

        if (socialOauth instanceof NaverOauth) {
            String accessToken = socialOauth.getAccessToken(code);
            String profileJson = ((NaverOauth) socialOauth).getUserProfile(accessToken);
            return socialOauth.getLoginResponseDto(profileJson);
        } else {
            String idToken = socialOauth.getAccessToken(code);
            return socialOauth.getLoginResponseDto(idToken);
        }
    }

    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }
}
