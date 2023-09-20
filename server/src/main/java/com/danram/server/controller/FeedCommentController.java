package com.danram.server.controller;

import com.danram.server.dto.request.comment.CommentAddRequestDto;
import com.danram.server.dto.request.comment.CommentEditRequestDto;
import com.danram.server.dto.response.comment.CommentAddResponseDto;
import com.danram.server.dto.response.comment.CommentEditResponseDto;
import com.danram.server.dto.response.comment.FeedCommentResponseDto;
import com.danram.server.service.comment.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/feed-comment")
public class FeedCommentController {
    private final CommentService commentService;

    @ApiOperation("피드 부모 댓글 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 부모 댓글 조회 성공")
    })
    @GetMapping("/parent")
    public ResponseEntity<List<FeedCommentResponseDto>> getParent(@RequestParam Integer pages, @RequestParam Long feedId) {
        return ResponseEntity.ok(commentService.findFeedParentComment(pages, feedId));
    }
    
    @ApiOperation("피드 자식 댓글 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 자식 댓글 조회 성공")
    })
    @GetMapping("/child")
    public ResponseEntity<List<FeedCommentResponseDto>> getChild(@RequestParam Integer pages, @RequestParam Long commentId) {
        return ResponseEntity.ok(commentService.findFeedChildComment(pages, commentId));
    }

    @ApiOperation("피드 댓글 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 댓글 추가 성공")
    })
    @PostMapping
    public ResponseEntity<CommentAddResponseDto> addComment(@RequestBody CommentAddRequestDto dto) {
        return ResponseEntity.ok(commentService.addFeedComment(dto));
    }

    @ApiOperation("피드 댓글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 댓글 수정 성공")
    })
    @PostMapping("/edit")
    public ResponseEntity<CommentEditResponseDto> editComment(@RequestBody CommentEditRequestDto dto) {
        return ResponseEntity.ok(commentService.editFeedComment(dto));
    }

    @ApiOperation("피드 댓글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 댓글 삭제")
    })
    @DeleteMapping
    public ResponseEntity<Boolean> deleteComment(@RequestParam Long commentId,@RequestParam Long feedId) {
        return ResponseEntity.ok(commentService.deleteFeedComment(commentId,feedId));
    }
}
