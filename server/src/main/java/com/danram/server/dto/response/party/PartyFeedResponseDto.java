package com.danram.server.dto.response.party;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PartyFeedResponseDto {
    private Long partyId;
    private String title;
    private LocalDate startedAt;
    private LocalDate endAt;
    private Boolean isManager;

    // 알람 시간
    // 알람 요일
    private String location;
    private Long currentCount;
    private Long max;
    private String description;
}
