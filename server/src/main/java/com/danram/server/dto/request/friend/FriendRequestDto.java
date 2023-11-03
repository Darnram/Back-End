package com.danram.server.dto.request.friend;

import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendRequestDto {
    @ApiParam(name = "nickname", value = "닉네임")
    private String nickname;
}
