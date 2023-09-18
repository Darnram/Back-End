package com.danram.server.dto.response.party;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyMemberResponseDto {
    private Long memberId;
    private String nickname;
}
