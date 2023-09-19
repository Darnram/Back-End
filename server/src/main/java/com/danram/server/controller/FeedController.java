package com.danram.server.controller;

import com.danram.server.dto.request.feed.FeedAddRequestDto;
import com.danram.server.dto.request.feed.FeedEditRequestDto;
import com.danram.server.dto.request.feed.FeedRequestDto;
import com.danram.server.dto.response.feed.FeedAddResponseDto;
import com.danram.server.dto.response.feed.FeedEditResponseDto;
import com.danram.server.dto.response.feed.FeedResponseDto;
import com.danram.server.service.feed.FeedService;
import com.danram.server.service.s3.S3UploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final S3UploadService s3UploadService;
    private final FeedService feedService;

    @ApiOperation("피드 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 메인 조회 성공")
    })
    @GetMapping
    public ResponseEntity<List<FeedResponseDto>> getFeedMain(@RequestParam Integer pages, @RequestParam Long partyId) {
        return ResponseEntity.ok(feedService.findFeed(pages,partyId));
    }

    @ApiOperation("피드 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 추가 성공")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FeedAddResponseDto> addFeed(@ModelAttribute FeedAddRequestDto dto) throws IOException {
        String imgUrl = null;

        if (dto.getFeedImg()!=null) {
            imgUrl = s3UploadService.upload(dto.getFeedImg(),"feed_image");
        }

        return ResponseEntity.ok(feedService.addFeed(dto,imgUrl));
    }

    @ApiOperation("피드 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 수정 성공")
    })
    @PostMapping(value = "/edit",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FeedEditResponseDto> editFeed(@ModelAttribute FeedEditRequestDto dto) throws IOException {
        String imgUrl = null;

        if (dto.getFeedImg() != null) {
            imgUrl = s3UploadService.upload(dto.getFeedImg(),"feed_image");
        }

        return ResponseEntity.ok(feedService.editFeed(dto,imgUrl));
    }

    @ApiOperation("피드 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "피드 삭제 성공")
    })
    @DeleteMapping
    public ResponseEntity<Boolean> deleteFeed(@RequestParam Long feedId) {
        return ResponseEntity.ok(feedService.deleteFeed(feedId));
    }
}
