package com.danram.server.controller;

import com.danram.server.dto.request.party.AddPartyRequestDto;
import com.danram.server.dto.request.party.PartyEditRequestDto;
import com.danram.server.dto.request.party.PartyJoinRequestDto;
import com.danram.server.dto.request.party.PartyKickRequestDto;
import com.danram.server.dto.response.feed.FeedResponseDto;
import com.danram.server.dto.response.party.*;
import com.danram.server.repository.PartyRepository;
import com.danram.server.service.party.PartyService;
import com.danram.server.service.s3.S3UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/party")
@RequiredArgsConstructor
public class PartyController {
    private final S3UploadService s3UploadService;
    private final PartyService partyService;

    @ApiOperation("모임 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "모임 추가 성공")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AddPartyResponseDto> addParty(@ModelAttribute AddPartyRequestDto dto) throws IOException {
        String imgUrl = null;

        if (dto.getImg() != null) {
            imgUrl = s3UploadService.upload(dto.getImg(),"party_image");
        } else {
            // 이미지 없으면 디폴트 이미지 저장
        }

        return ResponseEntity.ok(partyService.addParty(dto,imgUrl));
    }

    @ApiOperation("내 모임 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "내 모임 조회")
    })
    @GetMapping("/my")
    public ResponseEntity<List<PartyResponseDto>> getMyParty(@RequestParam Integer pages) {
        return ResponseEntity.ok(partyService.findMyParty(pages));
    }

    @ApiOperation("카테고리 선택X + 정렬 조건 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "모임 조회 성공")
    })
    @GetMapping
    public ResponseEntity<List<PartyResponseDto>> getParty(@RequestParam Long sortType,@RequestParam Integer pages) {
        return ResponseEntity.ok(partyService.findParty(sortType,pages));
    }

    @ApiOperation("카테고리 선택 + 정렬 조건 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "모임 조회 성공")
    })
    @GetMapping("/category")
    public ResponseEntity<List<PartyResponseDto>> getPartyByCategory(@RequestParam String partyType,@RequestParam Long sortType,@RequestParam Integer pages) {
        return ResponseEntity.ok(partyService.findPartyByPartyType(partyType,sortType,pages));
    }

    @ApiOperation("카테고리 선택X + 검색 + 정렬 조건 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "모임 조회 성공")
    })
    @GetMapping("/search")
    public ResponseEntity<List<PartyResponseDto>> getPartyBySearch(@RequestParam Long sortType,@RequestParam String query,@RequestParam Integer pages) {
        return ResponseEntity.ok(partyService.findPartyBySearch(sortType,query,pages));
    }
    
    @ApiOperation("카테고리 선택 + 검색 + 정렬 조건 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "모임 조회 성공")
    })
    @GetMapping("/search-category")
    public ResponseEntity<List<PartyResponseDto>> getPartyBySearchAndCategory(@RequestParam Long sortType,@RequestParam String query
                                                                             ,@RequestParam String partyType,@RequestParam Integer pages) {
        return ResponseEntity.ok(partyService.findPartyBySearchAndPartyType(sortType,query,partyType,pages));
    }

    @ApiOperation("모임에 참여")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "모임에 참여 성공")
    })
    @PostMapping("/join")
    public ResponseEntity<PartyJoinResponseDto> joinParty(@RequestBody PartyJoinRequestDto dto) {
        return ResponseEntity.ok(partyService.joinParty(dto));
    }

    @ApiOperation("모임에 참여중인 유저 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "모임에 참여중인 유저 조회 성공")
    })
    @GetMapping("/member/all")
    public ResponseEntity<List<PartyMemberResponseDto>> getPartyMember(@RequestParam Long partyId) {
        return ResponseEntity.ok(partyService.findPartyMember(partyId));
    }

    @ApiOperation("방장이 모임을 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "방장 모임 삭제 성공")
    })
    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteParty(@RequestParam Long partyId) {
        return ResponseEntity.ok(partyService.deleteParty(partyId));
    }

    @ApiOperation("모임 나가기")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "모임 나가기 성공")
    })
    @PostMapping("/exit")
    public ResponseEntity<Boolean> exitParty(@RequestParam Long partyId) {
        return ResponseEntity.ok(partyService.exitParty(partyId));
    }

    @ApiOperation("모임 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "모임 수정 성공")
    })
    @PostMapping("/edit")
    public ResponseEntity<PartyEditResponseDto> editParty(@ModelAttribute PartyEditRequestDto dto) throws IOException {
        String imgUrl = null;

        if (dto.getImg() != null) {
            imgUrl = s3UploadService.upload(dto.getImg(),"party_image");
        }

        return ResponseEntity.ok(partyService.editParty(dto,imgUrl));
    }

    @ApiOperation("유저 강퇴")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "유저 강퇴 성공")
    })
    @PostMapping("/kick")
    public ResponseEntity<Boolean> kickMember(@RequestBody PartyKickRequestDto dto) {
        return ResponseEntity.ok(partyService.kickMember(dto));
    }

    @ApiOperation("피드 관련 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 관련 정보 조회 성공")
    })
    @GetMapping("/feed")
    public ResponseEntity<PartyFeedResponseDto> getPartyFeed(@RequestParam Long partyId) {
        return ResponseEntity.ok(partyService.findPartyFeed(partyId));
    }
}
