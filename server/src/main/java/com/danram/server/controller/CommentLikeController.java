package com.danram.server.controller;

import com.danram.server.dto.request.comment.CommentLikeRequestDto;
import com.danram.server.dto.request.feedlike.FeedLikeAddRequestDto;
import com.danram.server.service.commentlike.CommentLikeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/comment-like")
@RequiredArgsConstructor
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @ApiOperation("피드 댓글 좋아요 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 좋아요 추가 성공")
    })
    @PostMapping
    public ResponseEntity<Boolean> addLike(@RequestBody CommentLikeRequestDto dto) {
        return ResponseEntity.ok(commentLikeService.addCommentLike(dto));
    }

    @ApiOperation("피드 댓글 좋아요 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 댓글 좋아요 취소 성공")
    })
    @PostMapping("/cancel")
    public ResponseEntity<Boolean> removeLike(@RequestBody CommentLikeRequestDto dto) {
        return ResponseEntity.ok(commentLikeService.removeCommentLike(dto));
    }
}
