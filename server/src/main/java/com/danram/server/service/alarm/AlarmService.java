package com.danram.server.service.alarm;

import com.danram.server.dto.request.alarm.AddAlarmRequestDto;
import com.danram.server.dto.response.alarm.AlarmResponseDto;

import java.util.List;

public interface AlarmService {
    public void addAlarm(AddAlarmRequestDto requestDto);

    public void deleteAlarm(Long alarmId);

    public AlarmResponseDto getAlarm(Long alarmId);

    public List<AlarmResponseDto> getPartyAlarmList(Long partyId);
}
