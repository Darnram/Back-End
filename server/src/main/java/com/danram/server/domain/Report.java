package com.danram.server.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", columnDefinition = "int")
    private Long reportId;

    @Column(name = "log_id", columnDefinition = "int")
    private Long logId;

    @Column(name = "member_id",columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "type", columnDefinition = "int")
    private Long type;

    @Column(name = "content", length = 50, columnDefinition = "varchar")
    private String content;

    @CreationTimestamp
    private LocalDate createdAt;
}
