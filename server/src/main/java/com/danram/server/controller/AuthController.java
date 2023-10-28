package com.danram.server.controller;

import com.danram.server.domain.Authority;
import com.danram.server.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
@Slf4j
@Api(tags = {"권한 API"})
public class AuthController {
    private final MemberService memberService;

    @ApiOperation(
            value = "사용자 권한 조회",
            notes = "사용자 권한을 조회한다."
    )
    @GetMapping
    public ResponseEntity<List<Authority>> getAuthority() {
        return ResponseEntity.ok(memberService.getAuthorities());
    }

}
