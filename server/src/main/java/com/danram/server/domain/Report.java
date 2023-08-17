package com.danram.server.domain;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "type", columnDefinition = "int")
    private Long type;

    @Column(name = "content_id", columnDefinition = "int")
    private Long contentId;

    @Column(name = "content", length = 50, columnDefinition = "varchar")
    private String content;

    @Column(name = "log_id", columnDefinition = "int")
    private String logId;
}
