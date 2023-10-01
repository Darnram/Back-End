package com.danram.server.dto.request.alarm;

import com.danram.server.enums.alarm.AlarmFrequencyEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
public class AddAlarmRequestDto {

    @Schema(description = "파티 id")
    private Long partyId;
    @Schema(description = "알람 시간")
    private LocalDateTime alarmTime;
    @Schema(description = "요일", allowableValues = "MON,TUE,WEN,TUR,FRI,SAT,SUN")
    List<AlarmFrequencyEnum> frequencies;
}
