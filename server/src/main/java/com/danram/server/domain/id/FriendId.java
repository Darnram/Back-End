package com.danram.server.domain.id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendId implements Serializable {
    private Long member;
    private Long friend;
}
