package com.danram.server.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "like_count", columnDefinition = "int")
    private Long likeCount;

    @Column(name = "comment_count",columnDefinition = "int")
    private Long commentCount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "feed_image",
            joinColumns = {@JoinColumn(name = "feed_id", referencedColumnName = "feed_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id", referencedColumnName = "image_id")})
    @ApiModelProperty(example = "feed images")
    private List<Image> images;
}
