package com.danram.server.service.feed;

import com.danram.server.config.MapperConfig;
import com.danram.server.domain.DateLog;
import com.danram.server.domain.Feed;
import com.danram.server.domain.Image;
import com.danram.server.domain.Member;
import com.danram.server.dto.request.feed.FeedAddRequestDto;
import com.danram.server.dto.response.feed.FeedAddResponseDto;
import com.danram.server.exception.feed.FeedMakeException;
import com.danram.server.exception.member.MemberIdNotFoundException;
import com.danram.server.repository.DateLogRepository;
import com.danram.server.repository.FeedRepository;
import com.danram.server.repository.ImageRepository;
import com.danram.server.repository.MemberRepository;
import com.danram.server.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.danram.server.config.MapperConfig.modelMapper;

@RequiredArgsConstructor
@Slf4j
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final DateLogRepository dateLogRepository;

    @Override
    @Transactional
    public FeedAddResponseDto addFeed(final FeedAddRequestDto dto, List<String> files) {
        FeedAddResponseDto map;
        //member 찾기 => 얘는 필터에서 조회하니까 예외처리 의미 없음
        Member member = memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId().toString())
        );
        //로그 생성
        DateLog dateLog = dateLogRepository.save(DateLog.of(JwtUtil.getMemberId(), member.getNickname() + "가 " + "feed 생성"));
        //이미지 리스트 생성
        List<Image> images = new ArrayList<>();

        if(files.isEmpty()) {
            if(dto.getContent().isBlank()) {// 이미지 X, 글 X
                throw new FeedMakeException("no image and no content");
            }
            else
            { //이미지 없고 글 있는거
                Feed feed = feedRepository.save(
                        Feed.builder()
                                .memberId(JwtUtil.getMemberId())
                                .commentCount(0L)
                                .content(dto.getContent())
                                .logId(dateLog.getLogId())
                                .partyId(dto.getPartyId())
                                .likeCount(0L)
                                .build()
                );

                map = modelMapper.map(feed, FeedAddResponseDto.class);

                return map;
            }
        }
        else
        { //이미지 있고 컨텐트는 노상관
            for(String image: files) { //이미지 저장 =>  객체로 만들기
                images.add(
                        imageRepository.save(
                                Image.builder()
                                        .imageUrl(image)
                                        .build()
                        )
                );
            }

            Feed feed = feedRepository.save(
                    Feed.builder()
                            .memberId(JwtUtil.getMemberId())
                            .commentCount(0L)
                            .content(dto.getContent().isBlank() ? null : dto.getContent())
                            .logId(dateLog.getLogId())
                            .partyId(dto.getPartyId())
                            .likeCount(0L)
                            .images(images)
                            .build()
            );

            map = modelMapper.map(feed, FeedAddResponseDto.class);

            return map;
        }
    }
}
