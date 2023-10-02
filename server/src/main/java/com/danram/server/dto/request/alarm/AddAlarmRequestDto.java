package com.danram.server.dto.request.alarm;

import com.danram.server.domain.Alarm;
import com.danram.server.domain.DateLog;
import com.danram.server.enums.alarm.AlarmFrequencyEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class AddAlarmRequestDto {

    @Schema(description = "파티 id")
    private Long partyId;
    @Schema(description = "알람 시간")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime alarmTime;
    @Schema(description = "요일", allowableValues = "MON,TUE,WEN,TUR,FRI,SAT,SUN")
    List<AlarmFrequencyEnum> frequencies;

    public Alarm toEntity(DateLog dateLog) {
        return Alarm.builder()
                .alarmId(null)
                .logId(dateLog.getLogId())
                .partyId(partyId)
                .alarmTime(alarmTime)
                .frequency(frequencies.stream().map(AlarmFrequencyEnum::getValue).collect(Collectors.joining()))
                .build();
    }

}
