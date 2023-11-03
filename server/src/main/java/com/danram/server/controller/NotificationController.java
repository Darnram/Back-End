package com.danram.server.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"알림 API"})
public class NotificationController {
    /**
     * TODO
     * 알림 보내기
     * */
}
