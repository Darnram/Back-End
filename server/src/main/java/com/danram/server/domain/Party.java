package com.danram.server.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id", columnDefinition = "int")
    private Long partyId;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "img", columnDefinition = "text")
    private String img;

    @Column(name = "title", length = 20, columnDefinition = "varchar")
    private String title;

    @Column(name = "description", length = 200, columnDefinition = "varchar")
    private String description;

    @Column(name = "password", length = 6, columnDefinition = "varchar")
    private String password;

    @Column(name = "party_type", length = 5, columnDefinition = "varchar")
    private String partyType;

    @Column(name = "max", columnDefinition = "int")
    private Long max;

    @Column(name = "started_at", columnDefinition = "date")
    private LocalDate startedAt;

    @Column(name = "end_at", columnDefinition = "date")
    private LocalDate endAt;

    @Column(name = "location", length = 50, columnDefinition = "varchar")
    private String location;

    @Column(name = "count", columnDefinition = "int")
    private Long count; //조회수
}
