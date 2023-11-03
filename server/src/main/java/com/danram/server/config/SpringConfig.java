package com.danram.server.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3;
import com.danram.server.repository.*;
import com.danram.server.service.chatting.DynamoDBService;
import com.danram.server.service.chatting.DynamoDBServiceImpl;
import com.danram.server.service.member.MemberService;
import com.danram.server.service.member.MemberServiceImpl;
import com.danram.server.service.party.PartyService;
import com.danram.server.service.party.PartyServiceImpl;
import com.danram.server.service.s3.S3UploadService;
import com.danram.server.service.s3.S3UploadServiceImpl;
import com.danram.server.service.token.TokenService;
import com.danram.server.service.token.TokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final DateLogRepository dateLogRepository;
    private final TokenRepository tokenRepository;
    private final PartyRepository partyRepository;
    private final AmazonS3 amazonS3;
    private final PartyMemberRepository partyMemberRepository;
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB amazonDynamoDB;

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository, tokenRepository);
    }

    @Bean
    public PartyService partyService() {
        return new PartyServiceImpl(partyRepository,partyMemberRepository,memberRepository);
    }

    @Bean
    public S3UploadService s3UploadService() {
        return new S3UploadServiceImpl(amazonS3);
    }

    @Bean
    public TokenService tokenService() {
        return new TokenServiceImpl(tokenRepository,memberRepository);
    }

    @Bean
    public DynamoDBService dynamoDBService() {
        return new DynamoDBServiceImpl(dynamoDBMapper, amazonDynamoDB);
    }
}
