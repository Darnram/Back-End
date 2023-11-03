package com.danram.server.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goal")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"달성률 API"})
public class GoalController {
    /**
     * TODO
     * 내 달성률 보기
     * 팀 달성률 보기
     * 달성 확인
     * 달성 취소
     * 일일 달성 리스트
     * */
}
