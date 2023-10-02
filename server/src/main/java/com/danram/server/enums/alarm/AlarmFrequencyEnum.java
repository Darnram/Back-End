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

    public static AlarmFrequencyEnum of(String value) {
        if ("월".equals(value)) return AlarmFrequencyEnum.MON;
        else if ("화".equals(value)) return AlarmFrequencyEnum.TUE;
        else if ("수".equals(value)) return AlarmFrequencyEnum.WEN;
        else if ("목".equals(value)) return AlarmFrequencyEnum.TUR;
        else if ("금".equals(value)) return AlarmFrequencyEnum.FRI;
        else if ("토".equals(value)) return AlarmFrequencyEnum.SAT;
        else if ("일".equals(value)) return AlarmFrequencyEnum.SUN;
        return null;
    }
}
