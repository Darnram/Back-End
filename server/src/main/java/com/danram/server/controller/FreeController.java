package com.danram.server.controller;

import com.danram.server.dto.response.party.PartyResponseDto;
import com.danram.server.service.member.MemberService;
import com.danram.server.service.party.PartyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("free")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"권한 필요 없는 api"})
public class FreeController {
    private final MemberService memberService;
    private final PartyService partyService;

    /**
     * TODO
     * 파티 검색, 조회 => Category, search, sort
     * 피드 조회
     * 댓글 조회
     * */

    @ApiOperation(value = "email 중복 체크")
    @GetMapping("/email")
    public ResponseEntity<Boolean> checkDuplicated(@RequestParam String email) {
        return ResponseEntity.ok(memberService.isDuplicatedEmail(email));
    }

    @ApiOperation(value = "party ")
    @GetMapping("/party/search")
    public ResponseEntity<List<PartyResponseDto>> searchParty(@RequestParam Long category, @RequestParam Long sort, @RequestParam String query, @RequestParam Long page) {
        if(category == 0L)
        {
            if(query.isBlank()) {
                return ResponseEntity.ok(partyService.findParty(sort, Integer.parseInt(page.toString())));
            }
            else
            {
                return ResponseEntity.ok(partyService.findPartyBySearch(sort, query, Integer.parseInt(page.toString())));
            }
        }
        else
        {
            if(query.isBlank()) {
                return ResponseEntity.ok(partyService.findPartyByPartyType(category, sort, Integer.parseInt(page.toString())));
            }
            else
            {
                return ResponseEntity.ok(partyService.findPartyBySearchAndPartyType(sort, query, category, Integer.parseInt(page.toString())));
            }
        }
    }
}
