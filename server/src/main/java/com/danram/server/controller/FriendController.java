package com.danram.server.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"친구 API"})
public class FriendController {
    /**
     * TODO
     * 친구 목록
     * 친구 검색
     * 친구 삭제
     * 친구 신청
     * */
}
