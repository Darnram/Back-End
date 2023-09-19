package com.danram.server.service.party;

import com.danram.server.dto.request.party.AddPartyRequestDto;
import com.danram.server.dto.request.party.PartyEditRequestDto;
import com.danram.server.dto.request.party.PartyJoinRequestDto;
import com.danram.server.dto.request.party.PartyKickRequestDto;
import com.danram.server.dto.response.party.*;
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

    PartyFeedResponseDto findPartyFeed(Long partyId);

    PartyJoinResponseDto joinParty(PartyJoinRequestDto dto);

    PartyEditResponseDto editParty(PartyEditRequestDto dto,String imgUrl);

    Boolean kickMember(PartyKickRequestDto dto);

    Boolean deleteParty(Long partyId);

    Boolean exitParty(Long partyId);
}
