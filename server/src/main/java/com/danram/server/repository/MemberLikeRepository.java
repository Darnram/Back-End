package com.danram.server.repository;

import com.danram.server.domain.MemberLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberLikeRepository extends JpaRepository<MemberLike,Long> {

    @Query("select ml from MemberLike ml where ml.member.memberId = :memberId and " +
            "ml.likeId.id = :id and " +
            "ml.likeId.type = :type and " +
            "ml.deletedAt is null")
    Optional<MemberLike> findActiveMemberLike(@Param("memberId") Long memberId, @Param("id") Long id,@Param("type") Long type);

    @Query("select ml from MemberLike ml where ml.member.memberId = :memberId and " +
            "ml.likeId.id = :id and " +
            "ml.likeId.type = :type")
    Optional<MemberLike> findByMemberIdAndId(@Param("memberId") Long memberId, @Param("id") Long id,@Param("type") Long type);
}
