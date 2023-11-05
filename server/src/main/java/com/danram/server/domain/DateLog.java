package com.danram.server.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "description",columnDefinition = "varchar",length = 100)
    private String description;

    @CreationTimestamp
    private LocalDate createdAt;

    public static DateLog of(Long id, String description) {
        return DateLog.builder()
                .memberId(id)
                .description(description)
                .createdAt(LocalDate.now())
                .build();
    }
}
