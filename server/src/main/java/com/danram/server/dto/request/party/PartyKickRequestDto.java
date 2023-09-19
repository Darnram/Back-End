package com.danram.server.dto.request.party;

import lombok.Getter;

@Getter
public class PartyKickRequestDto {
    private Long partyId;
    private Long memberId;
}
