package com.danram.server.service.party;

import com.danram.server.dto.request.party.AddPartyRequestDto;
import com.danram.server.dto.response.party.AddPartyResponseDto;

public interface PartyService {
    AddPartyResponseDto addParty(AddPartyRequestDto dto,String imgUrl);
}
