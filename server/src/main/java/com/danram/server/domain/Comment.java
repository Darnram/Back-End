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
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id" ,nullable = false,columnDefinition = "bigint")
    private Long commentId;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Member member;

    @JoinColumn(name = "log_id")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private DateLog dateLog;

    @Column(name = "content", length = 60, columnDefinition = "varchar")
    private String content;

    @Column(name = "id",columnDefinition = "bigint",nullable = false)
    private Long id;

    @Column(name = "parent_id", columnDefinition = "int")
    private Long parentId;

    @Column(name = "like_count", columnDefinition = "int")
    private Long likeCount;

    @Column(name = "comment_count",columnDefinition = "int")
    private Long commentCount;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @Column(name = "deleted_at",columnDefinition = "date")
    private LocalDate deletedAt;

    public void plusLikeCount() {
        this.likeCount += 1;
    }

    public void minusLikeCount() {
        this.likeCount -= 1;
    }

    public void plusCommentCount() {
        this.commentCount += 1;
    }

    public void minusCommentCount() {
        this.commentCount -= 1;
    }
}
