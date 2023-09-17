package com.danram.server.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Table(name = "date_log")
public class DateLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", columnDefinition = "int")
    private Long logId;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "description",columnDefinition = "varchar",length = 32)
    private String description;

    @Column(name = "created_at", columnDefinition = "date")
    private LocalDate createdAt;

    public static DateLog of(Long id) {
        return DateLog.builder()
                .memberId(id)
                .createdAt(LocalDate.now())
                .build();
    }
}
