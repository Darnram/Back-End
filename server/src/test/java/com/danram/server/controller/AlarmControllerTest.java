package com.danram.server.controller;

import com.danram.server.domain.Alarm;
import com.danram.server.domain.Party;
import com.danram.server.dto.request.alarm.AddAlarmRequestDto;
import com.danram.server.dto.response.alarm.AlarmResponseDto;
import com.danram.server.enums.alarm.AlarmFrequencyEnum;
import com.danram.server.exception.alarm.AlarmNotFoundException;
import com.danram.server.exception.party.PartyNotFoundException;
import com.danram.server.repository.AlarmRepository;
import com.danram.server.repository.PartyRepository;
import com.danram.server.service.alarm.AlarmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlarmControllerTest {


    @Autowired
    private AlarmController alarmController;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private AlarmRepository alarmRepository;

    @BeforeEach
    void setUp() {
        String role = "ROLE_USER";
        Long memberId = 1692201442270L;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, null, List.of(new SimpleGrantedAuthority(role)));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Test
    @Transactional
    @DisplayName(value = "알람 추가 테스트")
    void addAlarm() {
        AddAlarmRequestDto requestDto = new AddAlarmRequestDto();
        requestDto.setAlarmTime(LocalDateTime.now().plusDays(1L));
        requestDto.setFrequencies(Arrays.asList(new AlarmFrequencyEnum[]{
                AlarmFrequencyEnum.MON,
                AlarmFrequencyEnum.WEN,
                AlarmFrequencyEnum.FRI
        }));
        requestDto.setPartyId(1L);

        ResponseEntity<?> responseEntity = alarmController.addAlarm(requestDto);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(1L, alarmRepository.count());
    }

    @Test
    @Transactional
    @DisplayName(value = "알람 추가 테스트 (실패)")
    void addAlarm_Fail() {
        Long failPartyId = 9999L;

        AddAlarmRequestDto requestDto = new AddAlarmRequestDto();
        requestDto.setAlarmTime(LocalDateTime.now().plusDays(1L));
        requestDto.setFrequencies(Arrays.asList(new AlarmFrequencyEnum[]{
                AlarmFrequencyEnum.MON,
                AlarmFrequencyEnum.WEN,
                AlarmFrequencyEnum.FRI
        }));
        requestDto.setPartyId(failPartyId);

        assertThrows(PartyNotFoundException.class, () -> {
            alarmController.addAlarm(requestDto);
        });

        assertEquals(0L, alarmRepository.count());
    }

    @Test
    @Transactional
    @DisplayName(value = "알람 가져오기 테스트")
    void getAlarm() {
        AddAlarmRequestDto requestDto = new AddAlarmRequestDto();
        requestDto.setAlarmTime(LocalDateTime.now().plusDays(1L));
        requestDto.setFrequencies(Arrays.asList(new AlarmFrequencyEnum[]{
                AlarmFrequencyEnum.MON,
                AlarmFrequencyEnum.WEN,
                AlarmFrequencyEnum.FRI
        }));
        requestDto.setPartyId(1L);

        assertDoesNotThrow(() -> {
            alarmController.addAlarm(requestDto);
        });

        Alarm alarm = alarmRepository.findAll().get(0);

        List<ResponseEntity<AlarmResponseDto>> responseEntities = new ArrayList<>();

        assertDoesNotThrow(() -> {
            ResponseEntity<AlarmResponseDto> alarmResponse = alarmController.getAlarm(alarm.getAlarmId());
            responseEntities.add(alarmResponse);
        });

        assertEquals(1, responseEntities.size());
        AlarmResponseDto alarmResponseDto = responseEntities.get(0).getBody();
        assertNotNull(alarmResponseDto);
        assertEquals(alarm.getAlarmId(), alarmResponseDto.getAlarmId());
        assertEquals(3, alarmResponseDto.getFrequencies().size());
        assertTrue(LocalDateTime.now().isBefore(alarmResponseDto.getAlarmTime()));
    }

    @Test
    @Transactional
    @DisplayName(value = "알람 가져오기 테스트 (실패 - 알람 아이디가 없음)")
    void getAlarm_Fail() {

        Long notFoundAlarmId = 9999L;

        assertThrows(AlarmNotFoundException.class, () -> {
            alarmController.getAlarm(notFoundAlarmId);
        });
    }

    @Test
    @Transactional
    @DisplayName("파티 별 알람 가져오기 테스트")
    void getPartyAlarmList() {
        Long partyId = 1L;

        AddAlarmRequestDto requestDto = new AddAlarmRequestDto();
        requestDto.setAlarmTime(LocalDateTime.now().plusDays(1L));
        requestDto.setFrequencies(Arrays.asList(new AlarmFrequencyEnum[]{
                AlarmFrequencyEnum.MON,
                AlarmFrequencyEnum.WEN,
                AlarmFrequencyEnum.FRI
        }));
        requestDto.setPartyId(partyId);

        alarmController.addAlarm(requestDto);

        AddAlarmRequestDto requestDto2 = new AddAlarmRequestDto();
        requestDto.setAlarmTime(LocalDateTime.now().plusDays(1L));
        requestDto.setFrequencies(Arrays.asList(new AlarmFrequencyEnum[]{
                AlarmFrequencyEnum.SAT,
                AlarmFrequencyEnum.SUN
        }));
        requestDto.setPartyId(partyId);

        alarmController.addAlarm(requestDto);

        List<ResponseEntity<List<AlarmResponseDto>>> responseEntities = new ArrayList<>();

        assertDoesNotThrow(() -> {
            ResponseEntity<List<AlarmResponseDto>> responseEntity = alarmController.getPartyAlarmList(partyId);
            responseEntities.add(responseEntity);
        });
        assertEquals(1, responseEntities.size());

        List<AlarmResponseDto> alarmResponseDtoList = responseEntities.get(0).getBody();

        assertNotNull(alarmResponseDtoList);
        assertEquals(2, alarmResponseDtoList.size());
        assertEquals(3, alarmResponseDtoList.get(0).getFrequencies().size());
        assertEquals(2, alarmResponseDtoList.get(1).getFrequencies().size());
    }

    @Test
    @Transactional
    @DisplayName("파티 별 알람 가져오기 테스트 (실패 - 파티 아이디가 없음) ")
    void getPartyAlarmList_Fail() {
        Long partyId = 999L;

        assertThrows(PartyNotFoundException.class, () -> {
            alarmController.getPartyAlarmList(partyId);
        });
    }
}