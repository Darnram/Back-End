package com.danram.server.dto.response.alarm;

import com.danram.server.enums.alarm.AlarmFrequencyEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AlarmResponseDto {
    private Long alarmId;
    private LocalDateTime alarmTime;
    private List<AlarmFrequencyEnum> frequencies;
}
