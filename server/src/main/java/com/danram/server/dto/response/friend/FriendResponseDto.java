package com.danram.server.dto.response.friend;

import com.danram.server.domain.Friend;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendResponseDto {

    @Schema(name = "friends", description = "친구 목록")
    private List<FriendResponseItemDto> friends;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FriendResponseItemDto {

        @Schema(name = "nickname", description = "친구 닉네임")
        private String nickname;
        @Schema(name = "img", description = "친구 프로필 이미지")
        @JsonProperty(value = "profileImage")
        private String profileImage;

        public static FriendResponseItemDto of(Friend friend) {
            return FriendResponseItemDto.builder()
                    .nickname(friend.getFriend().getNickname())
                    .profileImage(friend.getFriend().getImg())
                    .build();
        }
    }
}
