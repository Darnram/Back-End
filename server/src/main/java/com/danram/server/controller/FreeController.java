package com.danram.server.controller;

import com.danram.server.service.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("free")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"권한 필요 없는 api"})
public class FreeController {
    private final MemberService memberService;

    @ApiOperation(value = "email 중복 체크")
    @GetMapping("/email")
    public ResponseEntity<Boolean> checkDuplicated(@RequestParam String email) {
        return ResponseEntity.ok(memberService.isDuplicatedEmail(email));
    }
}
