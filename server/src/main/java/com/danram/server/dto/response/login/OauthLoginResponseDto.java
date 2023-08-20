package com.danram.server.dto.response.login;

import com.danram.server.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthLoginResponseDto {
    private String nickname;
    private String email;
    private String profileImg;
    private Long loginType;

    public static OauthLoginResponseDto of(Member user) {
        return OauthLoginResponseDto.builder()
                .nickname(user.getNickname())
                .email(user.getEmail())
                .profileImg(user.getImg())
                .build();
    }
}
