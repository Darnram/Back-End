package com.danram.server.repository;

import com.danram.server.domain.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select c from Comment c where c.commentId = :commentId and " +
            "c.deletedAt is null")
    Optional<Comment> findByCommentId(@Param("commentId") Long commentId);

    @Query("select c from Comment c where c.member.memberId = :memberId and " +
            "c.commentId = :commentId and " +
            "c.deletedAt is null")
    Optional<Comment> findMyComment(@Param("memberId") Long memberId, @Param("commentId") Long commentId);

    @Query("select c from Comment c where c.parentId = :parentId and " +
            "c.deletedAt is null")
    List<Comment> findChildByParentId(@Param("parentId") Long parentId);

    @Query("select c from Comment c where c.id =:id and " +
            "c.deletedAt is null and " +
            "c.parentId is null")
    Slice<Comment> findParentComment(@Param("id") Long id,Pageable pageable);

    @Query("select c from Comment c where c.parentId = :parentId and " +
            "c.deletedAt is null")
    Slice<Comment> findChildComment(@Param("parentId") Long parentId, Pageable pageable);
}
