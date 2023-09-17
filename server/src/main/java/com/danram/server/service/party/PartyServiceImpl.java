package com.danram.server.service.party;

import com.danram.server.domain.DateLog;
import com.danram.server.domain.Party;
import com.danram.server.dto.request.party.AddPartyRequestDto;
import com.danram.server.dto.response.party.AddPartyResponseDto;
import com.danram.server.repository.DateLogRepository;
import com.danram.server.repository.MemberRepository;
import com.danram.server.repository.PartyRepository;
import com.danram.server.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import static com.danram.server.config.MapperConfig.modelMapper;

@Slf4j
public class PartyServiceImpl implements PartyService {
    private final PartyRepository partyRepository;
    private final MemberRepository memberRepository;
    private final DateLogRepository dateLogRepository;

    public PartyServiceImpl(final PartyRepository partyRepository,
                            final MemberRepository memberRepository,
                            final DateLogRepository dateLogRepository) {
        this.partyRepository = partyRepository;
        this.memberRepository = memberRepository;
        this.dateLogRepository = dateLogRepository;
    }

    @Override
    @Transactional
    public AddPartyResponseDto addParty(AddPartyRequestDto dto,String imgUrl) {
        //추후 추출한 이미지 URL로 설정
        Long memberId = JwtUtil.getMemberId();

        DateLog dateLog = DateLog.builder()
                .memberId(memberId)
                .description("유저가 모임을 생성")
                .build();

        dateLogRepository.save(dateLog);

        Party party = modelMapper.map(dto,Party.class);
        party.setMemberId(memberId);
        party.setLogId(dateLog.getLogId());
        party.setImg(imgUrl);
        party.setViewCount(0L);
        party.setCurrentCount(0L);

        partyRepository.save(party);

        return modelMapper.map(party, AddPartyResponseDto.class);
    }
}
