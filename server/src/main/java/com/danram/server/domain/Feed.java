package com.danram.server.domain;

import com.danram.server.dto.request.feed.FeedEditRequestDto;
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
@Table(name = "feed")
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id", columnDefinition = "int")
    private Long feedId;

    @JoinColumn(name = "party_id")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Party party;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Member member;

    @JoinColumn(name = "log_id")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private DateLog dateLog;

    @Column(name = "img", columnDefinition = "text")
    private String img;

    @Column(name = "content", columnDefinition = "text")
    private String content;

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

    public void updateFeed(FeedEditRequestDto dto,String imgUrl) {
        if (imgUrl != null) {
            this.img = imgUrl;
        }

        this.content = dto.getContent();
    }
}
