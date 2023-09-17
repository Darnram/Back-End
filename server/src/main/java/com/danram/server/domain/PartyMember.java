package com.danram.server.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Table(name = "party_member")
public class PartyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,columnDefinition = "bigint")
    private Long id;

    @Column(name = "member_id",nullable = false,columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "party_id",nullable = false,columnDefinition = "bigint")
    private Long partyId;

    @Column(name = "log_id",nullable = false,columnDefinition = "bigint")
    private Long logId;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @Column(name = "deleted_at",columnDefinition = "date")
    private LocalDate deletedAt;
}
