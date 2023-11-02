package com.danram.server.domain;

import com.danram.server.dto.request.party.PartyEditRequestDto;
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

    @Column(name = "manager_id", columnDefinition = "bigint")
    private Long managerId;

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

    @Column(name = "party_type", columnDefinition = "int")
    private Long partyType;

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

    public void updateParty(PartyEditRequestDto dto,String imgUrl) {
        if (imgUrl!=null) {
            this.img = imgUrl;
        }

        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.password = dto.getPassword();
        this.max = dto.getMax();
        this.location = dto.getLocation();
        this.managerId = dto.getManagerId();
    }
}
