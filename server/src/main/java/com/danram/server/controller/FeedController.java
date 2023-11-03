package com.danram.server.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"피드 API"})
public class FeedController {
    /**
     * TODO
     * 피드 수정
     * 피드 삭제
     * 피드 추가
     * 피드 신고
     * 알람 보기
     * 알람 추가
     * 알람 삭제
     * 알람 수정
     * 좋아요
     * */
}
