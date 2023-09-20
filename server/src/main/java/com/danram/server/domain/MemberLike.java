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
@Table(name = "member_like")
public class MemberLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id",nullable = false,columnDefinition = "bigint")
    private Long likeId;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Member member;

    @JoinColumn(name = "log_id")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private DateLog dateLog;

    @Column(name = "type",nullable = false,columnDefinition = "int")
    private Long type;

    @Column(name = "id",nullable = false,columnDefinition = "bigint")
    private Long id;

    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "deleted_at",columnDefinition = "date")
    private LocalDate deletedAt;
}
