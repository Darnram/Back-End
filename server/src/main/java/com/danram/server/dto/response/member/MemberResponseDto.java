package com.danram.server.dto.response.member;

import com.danram.server.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String nickname;
    private String img;
    private Boolean pro;
    private Boolean ban;

    public static MemberResponseDto of(Member member, ModelMapper mapper) {
        return mapper.map(member, MemberResponseDto.class);
    }
}
