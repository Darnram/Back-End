package com.danram.server.dto.response.feed;

import com.danram.server.domain.Member;
import com.danram.server.domain.MemberLike;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
public class FeedResponseDto {
    private Long feedId;
    private String memberImg;
    private String nickname;
    private String img;
    private String content;
    private Long likeCount;
    private Long commentCount;
    private LocalDate createdAt;
    private Boolean isLiked;
    private Boolean hasNextSlice;

    public void setMemberInfo(Member member) {
        this.memberImg = member.getImg();
        this.nickname = member.getNickname();
    }
}
