package com.danram.server.dto.request.friend;

import com.danram.server.domain.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqFriendDto {
    private Long memberId;

    public static Member toMemberEntity(Long memberId) {
        return Member.builder()
                .memberId(memberId)
                .build();
    }
}
