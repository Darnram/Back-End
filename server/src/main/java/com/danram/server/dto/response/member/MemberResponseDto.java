package com.danram.server.dto.response.member;

import com.danram.server.domain.Member;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String nickname;
    private String img;
    private Boolean pro;
    private Boolean ban;
    private Long loginType;

    public static MemberResponseDto of(Member member, ModelMapper mapper) {
        return mapper.map(member, MemberResponseDto.class);
    }
}
