package com.danram.server.domain;

import com.danram.server.domain.embeddable.LikeId;
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
@Table(name = "member_like")
public class MemberLike {
    @EmbeddedId
    private LikeId likeId;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Member member;

    @JoinColumn(name = "log_id")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private DateLog dateLog;

    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "deleted_at",columnDefinition = "date")
    private LocalDate deletedAt;
}
