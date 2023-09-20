package com.danram.server.repository;

import com.danram.server.domain.Feed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed,Long> {
    @Query("select f from Feed f where f.party.partyId = :partyId and" +
            " f.deletedAt is null")
    Slice<Feed> findByPartyId(@Param("partyId") Long partyId, Pageable pageable);

    @Query("select f from Feed f where f.member.memberId = :memberId and " +
            "f.feedId = :feedId and " +
            "f.deletedAt is null")
    Optional<Feed> findByMemberId(@Param("feedId") Long feedId,@Param("memberId") Long memberId);

    @Query("select f from Feed f where f.feedId = :feedId and " +
            "f.member.memberId = :memberId and " +
            "f.deletedAt is null")
    Optional<Feed> findByFeedIdAndMemberId(@Param("feedId") Long feedId,@Param("memberId") Long memberId);
}
