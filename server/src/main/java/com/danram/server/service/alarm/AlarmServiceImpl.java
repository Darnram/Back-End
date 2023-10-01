package com.danram.server.service.alarm;

import com.danram.server.domain.Alarm;
import com.danram.server.dto.request.alarm.AddAlarmRequestDto;
import com.danram.server.dto.response.alarm.AlarmResponseDto;
import com.danram.server.exception.alarm.AlarmNotFoundException;
import com.danram.server.exception.party.PartyNotFoundException;
import com.danram.server.repository.AlarmRepository;
import com.danram.server.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;
    private final PartyRepository partyRepository;


    @Override
    @Transactional
    public void addAlarm(AddAlarmRequestDto requestDto) {
        partyRepository.findById(requestDto.getPartyId())
                .orElseThrow(() -> new PartyNotFoundException("파티가 존재하지 않습니다."));
        alarmRepository.save(requestDto.toEntity());
    }

    @Override
    @Transactional
    public void deleteAlarm(Long alarmId) {
        Alarm alarmEntity = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new AlarmNotFoundException("해당하는 알림이 존재하지 않습니다."));
        // TODO: Soft Delete 처리
        // alarmRepository.delete(alarmEntity);
    }

    @Override
    public void deleteAlarmList(List<Long> alarmIds) {
        // TODO: Soft Delete 처리
        // alarmRepository.deleteAllById();
    }

    @Override
    @Transactional
    public AlarmResponseDto getAlarm(Long alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new AlarmNotFoundException("해당하는 알림이 존재하지 않습니다."));
        return AlarmResponseDto.of(alarm);
    }

    @Override
    @Transactional
    public List<AlarmResponseDto> getPartyAlarmList(Long partyId) {
        partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException("해당하는 파티가 존해하지 않습니다."));

        List<Alarm> alarmList = alarmRepository.findAllByPartyId(partyId);

        return alarmList.stream()
                .map(AlarmResponseDto::of)
                .collect(Collectors.toList());
    }
}
