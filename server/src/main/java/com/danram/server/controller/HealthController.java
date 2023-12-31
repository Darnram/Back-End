package com.danram.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
@Api(tags = {"HEALTH CHECK"})
@Slf4j
public class HealthController {

    @GetMapping("ping")
    @ApiOperation(value = "서버 상태를 체크한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답"),
    })
    public String pong() {
        return "pong";
    }
}
