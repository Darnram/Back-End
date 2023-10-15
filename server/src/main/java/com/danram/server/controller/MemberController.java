package com.danram.server.controller;

import com.danram.server.dto.response.member.MemberResponseDto;
import com.danram.server.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"유저 API"})
public class MemberController {
    private final MemberService memberService;

    /**
     * TODO
     * 사용자 정보 수정
     * 회원 탈퇴
     * 로그 아웃
     **/

    @ApiOperation(value = "사용자 정보 조회")
    @GetMapping
    public ResponseEntity<MemberResponseDto> getInfo() {
        return ResponseEntity.ok(memberService.getInfo());
    }
}
