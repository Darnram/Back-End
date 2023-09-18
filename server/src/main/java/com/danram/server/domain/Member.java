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
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "nickname", length = 15, columnDefinition = "varchar")
    private String nickname;

    @Column(name = "email", length = 30, columnDefinition = "varchar")
    private String email;

    @Column(name = "login_type", columnDefinition = "int")
    private Long loginType;

    @Column(name = "img", columnDefinition = "text")
    private String img;

    @Column(name = "pro", columnDefinition = "boolean")
    private Boolean pro;

    @Column(name = "ban", columnDefinition = "boolean")
    private Boolean ban;

    @JoinColumn(name = "log_id")
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private DateLog dateLog;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    @ApiModelProperty(example = "사용자 권한 정보들")
    private List<Authority> authorities;
}
