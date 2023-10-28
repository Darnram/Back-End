package com.danram.server.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"댓글 API"})
public class CommentController {
    /**
     * TODO
     * 댓글 수정
     * 댓글 삭제
     * 댓글 추가
     * 댓글 신고
     * 대댓글
     * 댓글 좋아요
     * 댓글 좋아요 취소
     * */
}
