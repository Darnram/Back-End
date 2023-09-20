package com.danram.server.dto.response.comment;

import com.danram.server.domain.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FeedCommentResponseDto {
    private Long feedId;
    private Long commentId;
    private String img;
    private String nickname;
    private String content;
    private Long likeCount;
    private Long commentCount;
    private Boolean isMyComment;
    private Boolean isLiked;
    private Boolean hasNextSlice;
    private LocalDate createdAt;

    public void setMemberInfo(Member member) {
        this.img = member.getImg();
        this.nickname = member.getNickname();
    }
}
