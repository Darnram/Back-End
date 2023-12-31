package com.danram.server.domain;

import com.danram.server.util.JwtUtil;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Table(name = "token")
public class Token {
    @Id
    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "access_token", columnDefinition = "text")
    private String accessToken;

    @Column(name = "access_token_expired_at", columnDefinition = "date")
    private LocalDate accessTokenExpiredAt;

    @Column(name = "refresh_token", columnDefinition = "text")
    private String refreshToken;

    @Column(name = "refresh_token_expired_at", columnDefinition = "date")
    private LocalDate refreshTokenExpiredAt;

    public static Token of(Member member) {
        return Token.builder()
                .memberId(member.getMemberId())
                .accessToken(JwtUtil.createJwt(member.getMemberId()))
                .accessTokenExpiredAt(LocalDate.now())
                .refreshToken(JwtUtil.createRefreshToken(member.getMemberId()))
                .refreshTokenExpiredAt(LocalDate.now())
                .build();
    }
}
