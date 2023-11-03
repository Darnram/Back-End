package com.danram.server.controller;

import com.danram.server.dto.response.member.MemberInfoResponseDto;
import com.danram.server.dto.response.member.MemberResponseDto;
import com.danram.server.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"관리자 API"})
public class AdminController {
    private final MemberService memberService;

    /**
     * TODO
     * 피드 관리
     * 댓글 관리
     * 차단 사용자 관리
     * 문의 내역 관리
     * 신고 관리
     * 관리자 아이디 추가
     * 사용자 탈퇴
     * */

    @ApiOperation(value = "모든 사용자 정보 조회")
    @GetMapping("/user/all")
    public ResponseEntity<List<MemberInfoResponseDto>> getAll() {
        return ResponseEntity.ok(memberService.getAll());
    }

    @ApiOperation(value = "특정 사용자 정보 확인")
    @ApiImplicitParam(
            name = "nickname",
            value = "사용자 닉네임",
            required = true,
            dataType = "string",
            defaultValue = "none"
    )
    @GetMapping("/user/info")
    public ResponseEntity<MemberResponseDto> getInfo(@RequestParam("nickname") String nickname) {
        return ResponseEntity.ok(memberService.findByNickname(nickname));
    }
}
