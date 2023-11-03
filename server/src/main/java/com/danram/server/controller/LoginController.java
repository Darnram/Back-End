package com.danram.server.controller;

import com.danram.server.domain.Member;
import com.danram.server.dto.response.login.LoginResponseDto;
import com.danram.server.dto.response.login.OauthLoginResponseDto;
import com.danram.server.service.login.OAuthService;
import com.danram.server.service.login.SocialLoginType;
import com.danram.server.service.member.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/login")
@Api(tags = {"로그인 API"})
@RequiredArgsConstructor
public class LoginController {
    private final OAuthService oauthService;
    private final MemberService memberService;

    /**
     * TODO
     * apple login
     * */

    @GetMapping(value = "/{socialLoginType}")
    public void socialLoginType(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oauthService.request(socialLoginType);
    }

    @GetMapping(value = "/{socialLoginType}/token")
    @Transactional
    public ResponseEntity<LoginResponseDto> codeCallBack(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
            @RequestParam(name = "code") String code) {
        OauthLoginResponseDto oauthLoginResponseDto = oauthService.getLoginResponseDto(socialLoginType,code);

        Optional<Member> result = memberService.checkDuplicatedEmail(oauthLoginResponseDto.getEmail());

        if(result.isEmpty()) {
            return ResponseEntity.ok(memberService.signUp(oauthLoginResponseDto));
        }
        else
        {
            return ResponseEntity.ok(memberService.signIn(result.get()));
        }
    }
}
