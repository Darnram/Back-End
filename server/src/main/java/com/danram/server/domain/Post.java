package com.danram.server.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partyId", columnDefinition = "int")
    private Long partyId;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "log_id", columnDefinition = "int")
    private Long logId;

    @Column(name = "img", columnDefinition = "text")
    private String img;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "heart", columnDefinition = "int")
    private Long heart;
}
