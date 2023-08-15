package com.danram.server.domain;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "comment_id", columnDefinition = "int")
    private Long commentId;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "content", length = 60, columnDefinition = "varchar")
    private String content;

    @Column(name = "parent_content", columnDefinition = "int")
    private Long parentContent;

    @Column(name = "heart", columnDefinition = "int")
    private Long heart;
}
