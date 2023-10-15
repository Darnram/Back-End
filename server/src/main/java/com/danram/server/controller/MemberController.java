package com.danram.server.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"유저 API"})
public class MemberController {
    /**
     * TODO
     * 사용자 정보 수정
     * 사용자 정보 확인
     * 회원 탈퇴
     * 로그 아웃
     **/
}
