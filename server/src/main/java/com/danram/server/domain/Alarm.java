package com.danram.server.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Table(name = "alarm")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id", columnDefinition = "int")
    private Long alarmId;

    @Column(name = "party_id", columnDefinition = "int")
    private Long partyId;

    @Column(name = "alarm_time", columnDefinition = "time")
    private LocalDateTime alarmTime;

    @Column(name = "frequency", length = 8, columnDefinition = "varchar")
    private String frequency;

    @Column(name = "log_id", columnDefinition = "int")
    private Long logId;
}
