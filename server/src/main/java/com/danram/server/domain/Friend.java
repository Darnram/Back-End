package com.danram.server.domain;

import com.danram.server.domain.id.FriendId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@IdClass(FriendId.class)
@Table(name = "friend")
public class Friend {

    @Id
    @NotNull
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Id
    @NotNull
    @JoinColumn(name = "friend_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member friend;

}