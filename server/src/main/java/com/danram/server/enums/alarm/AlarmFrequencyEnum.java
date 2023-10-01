package com.danram.server.enums.alarm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlarmFrequencyEnum {
    MON("월"),
    TUE("화"),
    WEN("수"),
    TUR("목"),
    FRI("금"),
    SAT("토"),
    SUN("일");

    private String value;
}
