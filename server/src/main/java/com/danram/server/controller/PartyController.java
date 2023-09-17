package com.danram.server.controller;

import com.danram.server.dto.request.party.AddPartyRequestDto;
import com.danram.server.dto.response.party.AddPartyResponseDto;
import com.danram.server.service.party.PartyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/party")
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;

    @ApiOperation("모임 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "모임 추가 성공")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AddPartyResponseDto> addParty(@ModelAttribute AddPartyRequestDto dto) {
        // S3 이미지 업로드 로직 필요
        // 이미지 URL 추출 로직 필요
        String imgUrl = null;

        return ResponseEntity.ok(partyService.addParty(dto,imgUrl));
    }
}
