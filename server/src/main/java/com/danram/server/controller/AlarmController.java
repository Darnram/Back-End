package com.danram.server.controller;

import com.danram.server.dto.request.alarm.AddAlarmRequestDto;
import com.danram.server.dto.response.alarm.AlarmResponseDto;
import com.danram.server.service.alarm.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> addAlarm(@RequestBody AddAlarmRequestDto addAlarmRequestDto) {
        alarmService.addAlarm(addAlarmRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{alarmId}")
    @ApiOperation(value = "알람 삭제")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "알람 삭제 성공")
    )
    public ResponseEntity<?> deleteAlarm(@PathVariable(value = "alarmId") Long alarmId) {
        alarmService.deleteAlarm(alarmId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "알람 삭제 (다건)")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "알람 삭제 성공")
    )
    public void deleteAlarmList(@PathParam(value = "alarmIds") String alarmIds) {
        List<Long> alarmIdList = Arrays.stream(alarmIds.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        alarmService.deleteAlarmList(alarmIdList);
    }

    @GetMapping(value = "/{alarmId}")
    @ApiOperation(value = "알람 가져오기")
    @ApiResponses(
        @ApiResponse(responseCode = "200", description = "알람 가져오기 성공")
    )
    public ResponseEntity<AlarmResponseDto> getAlarm(@PathVariable(value = "alarmId") Long alarmId) {
        return ResponseEntity.ok(alarmService.getAlarm(alarmId));
    }

    @GetMapping(value = "/party/{partyId}")
    @ApiOperation(value = "파티 알람 가져오기")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "알람 가져오기 성공")
    )
    public ResponseEntity<List<AlarmResponseDto>> getPartyAlarmList(@PathVariable(value = "partyId") Long partyId) {
        return ResponseEntity.ok(alarmService.getPartyAlarmList(partyId));
    }
}
