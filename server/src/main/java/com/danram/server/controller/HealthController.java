package com.danram.server.controller;

import com.danram.server.domain.Authority;
import com.danram.server.domain.DateLog;
import com.danram.server.domain.Member;
import com.danram.server.repository.DateLogRepository;
import com.danram.server.repository.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
@Api(tags = {"HEALTH CHECK"})
@Slf4j
public class HealthController {
    private final DateLogRepository dateLogRepository;
    private final MemberRepository memberRepository;

    @GetMapping("ping")
    @ApiOperation(value = "서버 상태를 체크한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답"),
    })
    public String pong() {
        //given
        Authority user = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Authority admin = Authority.builder()
                .authorityName("ROLE_ADMIN")
                .build();

        Long id = System.currentTimeMillis();

        log.info("id: {}", id);

        DateLog dateLog = DateLog.builder()
                .memberId(id)
                .createdAt(LocalDate.now())
                .build();

        final DateLog save = dateLogRepository.save(dateLog);

        log.info("dateLog: {}", dateLog);

        Member member = Member.builder()
                .memberId(id)
                .authorities(Arrays.asList(user, admin))
                .ban(false)//
                .email("djs9844@naver.com")//
                .img("https://img3.daumcdn.net/thumb/R658x0.q70/?fname=https://t1.daumcdn.net/news/202301/03/startoday/20230103103009408tnvd.jpg")
                .dateLog(save)
                .loginType(0L)//
                .nickname("지승언")//
                .pro(true)//
                .build();

        log.info("member id: {}", member.getMemberId());
        log.info("member authorities: {}", member.getAuthorities());
        log.info("member ban: {}", member.getBan());
        log.info("member email: {}", member.getEmail());
        log.info("img: {}", member.getImg());
        //log.info("log id: {}", member.getDateLog());
        log.info("login type: {}", member.getLoginType());
        log.info("nickname: {}", member.getNickname());
        log.info("pro: {}", member.getPro());



        //when
        final Member member1 = memberRepository.save(member);

        return member1.getMemberId().toString();
    }
}
