package com.danram.server.dto.response.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberInfoResponseDto {
    private String email;
    private String nickname;
    private String LoginType;
    private String img;
    private Boolean pro;
    private Boolean ban;
    private LocalDate createdAt;
}
