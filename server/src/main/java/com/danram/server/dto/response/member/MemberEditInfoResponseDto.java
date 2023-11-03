package com.danram.server.dto.response.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEditInfoResponseDto {
    private String nickname;
    private String img;
    private String email;
    private String loginType;
}
