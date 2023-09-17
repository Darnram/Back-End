package com.danram.server.domain;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "party_id",columnDefinition = "int")
    private Long partyId;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "log_id", columnDefinition = "int")
    private Long logId;

    @Column(name = "img", columnDefinition = "text")
    private String img;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "like_count", columnDefinition = "int")
    private Long likeCount;

    @Column(name = "comment_count",columnDefinition = "int")
    private Long commentCount;
}
