package com.danram.server.service.alarm;

import com.danram.server.domain.Alarm;
import com.danram.server.domain.DateLog;
import com.danram.server.dto.request.alarm.AddAlarmRequestDto;
import com.danram.server.dto.response.alarm.AlarmResponseDto;
import com.danram.server.exception.alarm.AlarmNotFoundException;
import com.danram.server.exception.party.PartyNotFoundException;
import com.danram.server.repository.AlarmRepository;
import com.danram.server.repository.DateLogRepository;
import com.danram.server.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;
    private final DateLogRepository dateLogRepository;
    private final PartyRepository partyRepository;


    @Override
    @Transactional
    public void addAlarm(AddAlarmRequestDto requestDto) {
        partyRepository.findById(requestDto.getPartyId())
                .orElseThrow(() -> new PartyNotFoundException("파티가 존재하지 않습니다."));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long memberId = (Long) principal;

        DateLog savedDateLog = dateLogRepository.save(new DateLog(null, memberId, memberId + " id 유저가 " + requestDto.getPartyId() + " 파티에 알람을 추가함", LocalDateTime.now().toLocalDate()));
        alarmRepository.save(requestDto.toEntity(savedDateLog));
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
