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
@Table(name = "inquiry")
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id", columnDefinition = "int")
    private Long inquiryId;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "created_at", columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @Column(name = "answer", columnDefinition = "tinyint")
    private Boolean answer;
}
