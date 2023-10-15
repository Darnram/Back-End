package com.danram.server.service.party;

import com.danram.server.domain.DateLog;
import com.danram.server.domain.Member;
import com.danram.server.domain.Party;
import com.danram.server.domain.PartyMember;
import com.danram.server.dto.request.party.AddPartyRequestDto;
import com.danram.server.dto.request.party.PartyEditRequestDto;
import com.danram.server.dto.request.party.PartyJoinRequestDto;
import com.danram.server.dto.response.party.*;
import com.danram.server.exception.party.*;
import com.danram.server.exception.member.MemberIdNotFoundException;
import com.danram.server.repository.MemberRepository;
import com.danram.server.repository.PartyMemberRepository;
import com.danram.server.repository.PartyRepository;
import com.danram.server.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.danram.server.config.MapperConfig.modelMapper;

@Slf4j
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService {
    private final PartyRepository partyRepository;
    private final PartyMemberRepository partyMemberRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public AddPartyResponseDto addParty(AddPartyRequestDto dto,String imgUrl) {
        //추후 추출한 이미지 URL로 설정
        Long memberId = JwtUtil.getMemberId();

        DateLog dateLog = DateLog.builder()
                .memberId(memberId)
                .description("유저가 모임을 생성")
                .build();

        Party party = modelMapper.map(dto,Party.class);
        party.setManagerId(memberId);
        party.setDateLog(dateLog);
        party.setImg(imgUrl);
        party.setStartedAt(dto.getStatedAt());
        party.setEndAt(dto.getEndAt());
        party.setViewCount(0L);
        party.setCurrentCount(1L);

        partyRepository.save(party);

        return modelMapper.map(party, AddPartyResponseDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartyMemberResponseDto> findPartyMember(Long partyId) {
        List<PartyMemberResponseDto> responseDtoList = new ArrayList<>();

        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException(partyId.toString()));

        List<PartyMember> partyMemberList = partyMemberRepository.findByParty(party);

        for (PartyMember partyMember : partyMemberList) {
            Member member = partyMember.getMember();
            PartyMemberResponseDto responseDto = modelMapper.map(member, PartyMemberResponseDto.class);
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartyResponseDto> findParty(Long sortType, Integer pages) {
        Pageable pageable = getPageable(sortType, pages);
        Slice<Party> partyList = partyRepository.findParty(pageable);

        return convertPartySliceToList(partyList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartyResponseDto> findPartyByPartyType(String partyType, Long sortType, Integer pages) {
        Pageable pageable = getPageable(sortType, pages);
        Slice<Party> partyList = partyRepository.findPartyByPartyType(partyType,pageable);

        return convertPartySliceToList(partyList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartyResponseDto> findPartyBySearch(Long sortType, String query, Integer pages) {
        Pageable pageable = getPageable(sortType,pages);
        Slice<Party> partyList = partyRepository.findPartyBySearch(query,pageable);

        return convertPartySliceToList(partyList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartyResponseDto> findPartyBySearchAndPartyType(Long sortType, String query, String partyType, Integer pages) {
        Pageable pageable = getPageable(sortType,pages);
        Slice<Party> partyList = partyRepository.findPartyBySearchAndPartyType(query,partyType,pageable);

        return convertPartySliceToList(partyList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartyResponseDto> findMyParty(Integer pages) {
        List<PartyResponseDto> responseDtoList = new ArrayList<>();

        Long memberId = JwtUtil.getMemberId();

        Pageable pageable = getPageable(0L,pages);
        Slice<PartyMember> partyMemberList = partyMemberRepository.findByMemberId(memberId,pageable);

        for (PartyMember partyMember : partyMemberList) {
            Party party = partyMember.getParty();
            PartyResponseDto responseDto = modelMapper.map(party, PartyResponseDto.class);
            responseDto.setHasNextSlice(partyMemberList.hasNext());
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    @Override
    @Transactional
    public PartyJoinResponseDto joinParty(PartyJoinRequestDto dto) {
        Long memberId = JwtUtil.getMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberIdNotFoundException(memberId.toString()));

        Party party = partyRepository.findById(dto.getPartyId())
                .orElseThrow(() -> new PartyNotFoundException(dto.getPartyId().toString()));

        DateLog dateLog = DateLog.builder()
                .memberId(memberId)
                .description(String.format("%d id 사용자가 %d id 모임에 가입",memberId,party.getPartyId()))
                .build();

        PartyMember partyMember;
        Optional<PartyMember> checkMember = partyMemberRepository.findByPartyAndMember(party,member);
        
        if (checkMember.isPresent() && checkMember.get().getDeletedAt() == null) { // 중복 가입 예외
            throw new PartyMemberDuplicatedException(member.getMemberId().toString());
        } else if (checkMember.isPresent() && checkMember.get().getDeletedAt() != null) { // 가입한 적 있음. 탈퇴 후 3일 경과 여부 체크
            Period period = Period.between(checkMember.get().getDeletedAt(),LocalDate.now());
            
            if (period.getDays() <= 3) { // 3일 안지남. 가입 불가
                throw new PartyJoinNotAllowException();
            } else { // 3일 경과 가입 가능
                partyMember = checkMember.get();
                partyMember.setDeletedAt(null);
            }
        } else { // 가입한 적 없는 유저.
            partyMember = PartyMember.builder()
                    .member(member)
                    .party(party)
                    .dateLog(dateLog)
                    .build();
        }

        party.plusCurrentCount();

        partyMemberRepository.save(partyMember);

        return modelMapper.map(partyMember, PartyJoinResponseDto.class);
    }

    @Override
    @Transactional
    public Boolean deleteParty(Long partyId) {
        Long memberId = JwtUtil.getMemberId();

        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException(partyId.toString()));

        if (!party.getManagerId().equals(memberId)) {
            throw new InvalidHostException(memberId.toString());
        }
        party.setDeletedAt(LocalDate.now());

        List<PartyMember> partyMemberList = partyMemberRepository.findByParty(party);

        for (PartyMember partyMember : partyMemberList) {
            partyMember.setDeletedAt(LocalDate.now());
        }

        return true;
    }

    @Override
    @Transactional
    public Boolean exitParty(Long partyId) {
        Long memberId = JwtUtil.getMemberId();

        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new PartyNotFoundException(partyId.toString()));

        PartyMember partyMember = partyMemberRepository.findByMemberIdAndParty(memberId,party)
                .orElseThrow(() -> new PartyNotFoundException(partyId.toString()));

        if (party.getManagerId().equals(memberId)) {
            throw new PartyHostExitException(memberId.toString());
        }

        partyMember.setDeletedAt(LocalDate.now());
        party.minusCurrentCount();

        return true;
    }

    @Override
    @Transactional
    public PartyEditResponseDto editParty(PartyEditRequestDto dto,String imgUrl) {
        Long memberId = JwtUtil.getMemberId();

        Party party = partyRepository.findById(dto.getPartyId())
                .orElseThrow(() -> new PartyNotFoundException(dto.getPartyId().toString()));

        if (!party.getManagerId().equals(memberId)) {
            throw new NotPartyManagerException(memberId.toString());
        }

        party.updateParty(dto,imgUrl);
        partyRepository.save(party);

        return modelMapper.map(party, PartyEditResponseDto.class);
    }

    private Pageable getPageable(Long sortType, Integer pages) {
        Pageable pageable;

        if (sortType == 0L) {
            Sort sort = Sort.by(Sort.Direction.DESC,"createdAt");
            pageable = PageRequest.of(pages,10,sort);
        } else {
            Sort sort = Sort.by(Sort.Direction.DESC,"viewCount");
            pageable = PageRequest.of(pages,10,sort);
        }

        return pageable;
    }

    private List<PartyResponseDto> convertPartySliceToList(Slice<Party> partyList) {
        List<PartyResponseDto> responseDtoList = new ArrayList<>();

        for (Party party : partyList) {
            PartyResponseDto dto = modelMapper.map(party, PartyResponseDto.class);
            dto.setHasNextSlice(partyList.hasNext());
            responseDtoList.add(dto);
        }

        return responseDtoList;
    }
}
