package com.danram.server.service.alarm;

import com.danram.server.dto.request.alarm.AddAlarmRequestDto;
import com.danram.server.dto.response.alarm.AlarmResponseDto;
import com.danram.server.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;


    @Override
    public void addAlarm(AddAlarmRequestDto requestDto) {

    }

    @Override
    public void deleteAlarm(Long alarmId) {

    }

    @Override
    public AlarmResponseDto getAlarm(Long alarmId) {
        return null;
    }

    @Override
    public List<AlarmResponseDto> getPartyAlarmList(Long partyId) {
        return null;
    }
}
