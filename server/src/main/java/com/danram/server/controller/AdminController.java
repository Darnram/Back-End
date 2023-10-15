package com.danram.server.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"관리자 API"})
public class AdminController {
    /**
     * TODO
     * 피드 관리
     * 댓글 관리
     * 차단 사용자 관리
     * 문의 내역 관리
     * 신고 관리
     * 모든 사용자 정보
     * 관리자 아이디 추가
     * 사용자 탈퇴
     * */
}
