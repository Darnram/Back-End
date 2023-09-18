package com.danram.server.controller;

import com.danram.server.dto.request.token.TokenRequestDto;
import com.danram.server.dto.response.token.TokenResponseDto;
import com.danram.server.service.token.TokenService;
import io.swagger.annotations.Api;
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
@RequestMapping(value = "/token")
@Api(tags = {"토큰 API"})
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @GetMapping("check")
    @ApiOperation(value = "토큰이 만료됐는지 확인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상 응답"),
    })
    public ResponseEntity<Boolean> isExpiredAt() {
        return ResponseEntity.ok(tokenService.isExpired());
    }

    @ApiOperation("토큰 재발급")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "토큰 재발급 성공")
    })
    @PostMapping("/reissue")
    public ResponseEntity<TokenResponseDto> reissueAccessToken(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(tokenService.reissueAccessToken(tokenRequestDto.getMemberId()));
    }
}
