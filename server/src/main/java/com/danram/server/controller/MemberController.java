package com.danram.server.controller;

import com.danram.server.dto.request.member.MemberEditRequestDto;
import com.danram.server.dto.response.member.MemberInfoResponseDto;
import com.danram.server.dto.response.member.MemberResponseDto;
import com.danram.server.service.member.MemberService;
import com.danram.server.service.s3.S3UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"유저 API"})
public class MemberController {
    private final MemberService memberService;
    private final S3UploadService s3UploadService;

    /**
     * TODO
     * 사용자 정보 수정
     * 회원 탈퇴
     * 로그 아웃
     * 닉네임 중복 체크
     * 이메일 중복 체크
     * 회원 정보 찾기
     **/

    @ApiOperation(value = "사용자 정보 조회")
    @GetMapping
    public ResponseEntity<MemberResponseDto> getInfo() {
        return ResponseEntity.ok(memberService.getInfo());
    }

    @ApiOperation(value = "사용자 정보 수정")
    @PostMapping(value = "/edit", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MemberInfoResponseDto> editInfo(@ModelAttribute MemberEditRequestDto memberEditRequestDto) throws IOException {
        String upload = "";

        if(memberEditRequestDto.getImg() != null)
            upload = s3UploadService.upload(memberEditRequestDto.getImg(), "danram/profile", true);

        return ResponseEntity.ok(memberService.editInfo(memberEditRequestDto, upload));
    }
}
