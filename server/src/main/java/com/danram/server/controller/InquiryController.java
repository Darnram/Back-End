package com.danram.server.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inquiry")
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"문의 API"})
public class InquiryController {
    /**
     * 문의하기
     * 문의 내역 수정
     * 답변
     * */
}
