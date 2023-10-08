package com.danram.server.repository;

import com.danram.server.domain.Member;
import com.danram.server.domain.Party;
import com.danram.server.domain.PartyMember;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartyMemberRepository extends JpaRepository<PartyMember,Long> {

    @Query("select pm from PartyMember pm where pm.member.memberId = :memberId and " +
            "pm.deletedAt is null")
    Slice<PartyMember> findByMemberId(Long memberId, Pageable pageable);

    @Query("select pm from PartyMember pm where pm.member.memberId = :memberId and " +
            "pm.party = :party and " +
            "pm.deletedAt is null")
    Optional<PartyMember> findByMemberIdAndParty(@Param("memberId") Long memberId,@Param("party") Party party);

    List<PartyMember> findByParty(Party party);

    Optional<PartyMember> findByPartyAndMember(Party party, Member member);
}
