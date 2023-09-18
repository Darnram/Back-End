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
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id", columnDefinition = "bigint")
    private Long partyId;

    @JoinColumn(name = "member_id")
    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @JoinColumn(name = "log_id")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private DateLog dateLog;

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

    @Column(name = "current_count",columnDefinition = "int")
    private Long currentCount;

    @Column(name = "started_at", columnDefinition = "date")
    private LocalDate startedAt;

    @Column(name = "end_at", columnDefinition = "date")
    private LocalDate endAt;

    @Column(name = "location", length = 50, columnDefinition = "varchar")
    private String location;

    @Column(name = "view_count", columnDefinition = "int")
    private Long viewCount; //조회수

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @Column(name = "deleted_at",columnDefinition = "date")
    private LocalDate deletedAt;

    public void plusCurrentCount() {
        this.currentCount += 1L;
    }

    public void minusCurrentCount() {
        this .currentCount -= 1L;
    }
}
