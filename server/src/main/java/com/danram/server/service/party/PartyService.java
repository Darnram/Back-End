package com.danram.server.service.party;

import com.danram.server.dto.request.party.AddPartyRequestDto;
import com.danram.server.dto.request.party.PartyJoinRequestDto;
import com.danram.server.dto.response.party.AddPartyResponseDto;
import com.danram.server.dto.response.party.PartyJoinResponseDto;
import com.danram.server.dto.response.party.PartyMemberResponseDto;
import com.danram.server.dto.response.party.PartyResponseDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PartyService {
    AddPartyResponseDto addParty(AddPartyRequestDto dto,String imgUrl);

    List<PartyResponseDto> findParty(Long sortType,Integer pages);
    List<PartyResponseDto> findMyParty(Integer pages);
    List<PartyResponseDto> findPartyByPartyType(String partyType,Long sortType,Integer pages);
    List<PartyResponseDto> findPartyBySearch(Long sortType,String query,Integer pages);
    List<PartyResponseDto> findPartyBySearchAndPartyType(Long sortType,String query,String partyType,Integer pages);

    List<PartyMemberResponseDto> findPartyMember(Long partyId);

    PartyJoinResponseDto joinParty(PartyJoinRequestDto dto);

    Boolean deleteParty(Long partyId);

    Boolean exitParty(Long partyId);
}
