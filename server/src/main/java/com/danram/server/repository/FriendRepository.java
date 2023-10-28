package com.danram.server.repository;

import com.danram.server.domain.Friend;
import com.danram.server.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Member> {

    List<Friend> findAllByMember(Member memberId);
}