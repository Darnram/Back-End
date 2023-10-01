package com.danram.server.controller;

import com.danram.server.service.alarm.AlarmService;
import com.danram.server.service.alarm.AlarmServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alarm")
@RequiredArgsConstructor
@Api(tags = {"알람 API"})
@Slf4j
public class AlarmController {

    private final AlarmService alarmService;


    @PostMapping(value = "")
    @ApiOperation(value = "알람 추가")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "알람 추가 성공")
    )
    public void addAlarm() {

    }

    @DeleteMapping(value = "/{alarmId}")
    @ApiOperation(value = "알람 삭제")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "알람 삭제 성공")
    )
    public void deleteAlarm() {

    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "알람 삭제 (다건)")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "알람 삭제 성공")
    )
    public void deleteAlarmList() {

    }

    @GetMapping(value = "/{alarmId}")
    @ApiOperation(value = "알람 가져오기")
    @ApiResponses(
        @ApiResponse(responseCode = "200", description = "알람 가져오기 성공")
    )
    public void getAlarm() {

    }

    @GetMapping(value = "/party/{partyId}")
    @ApiOperation(value = "파티 알람 가져오기")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "알람 가져오기 성공")
    )
    public void getPartyAlarmList() {

    }
}
