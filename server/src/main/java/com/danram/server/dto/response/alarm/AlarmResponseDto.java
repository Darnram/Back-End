package com.danram.server.dto.response.alarm;

import com.danram.server.domain.Alarm;
import com.danram.server.enums.alarm.AlarmFrequencyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class AlarmResponseDto {
    private Long alarmId;
    private LocalDateTime alarmTime;
    private List<AlarmFrequencyEnum> frequencies;

    public static AlarmResponseDto of(Alarm alarmEntity) {
        return new AlarmResponseDto(
                alarmEntity.getAlarmId(),
                alarmEntity.getAlarmTime(),
                Arrays.stream(alarmEntity.getFrequency().split(""))
                        .map(AlarmFrequencyEnum::valueOf)
                        .collect(Collectors.toList())
        );
    }
}
