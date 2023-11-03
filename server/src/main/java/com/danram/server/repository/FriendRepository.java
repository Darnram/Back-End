package com.danram.server.repository;

import com.danram.server.domain.Friend;
import com.danram.server.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Member> {

    List<Friend> findAllByMember(Member memberId);

    List<Friend> findAllByMemberAndFriend(@NotNull Member member, @NotNull Member friend);
    List<Friend> findAllByMemberAndFriend_NicknameContains(@NotNull Member member, String friend_nickname);
}