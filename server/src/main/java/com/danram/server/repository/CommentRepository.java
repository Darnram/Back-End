package com.danram.server.repository;

import com.danram.server.domain.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select c from Comment c where c.commentPk.commentId = :commentId and" +
            " c.commentPk.type = :type and " +
            "c.deletedAt is null")
    Optional<Comment> findByCommentIdAndType(@Param("commentId") Long commentId, @Param("type") Long type);

    @Query("select c from Comment c where c.member.memberId = :memberId and " +
            "c.commentPk.commentId = :commentId and " +
            "c.commentPk.type = :type and " +
            "c.deletedAt is null")
    Optional<Comment> findMyComment(@Param("memberId") Long memberId, @Param("commentId") Long commentId,@Param("type") Long type);

    @Query("select c from Comment c where c.parentId = :parentId and " +
            "c.commentPk.type = :type and " +
            "c.deletedAt is null")
    List<Comment> findChildByParentId(@Param("parentId") Long parentId,@Param("type") Long type);

    @Query("select c from Comment c where c.id =:id and " +
            "c.commentPk.type = :type and " +
            "c.deletedAt is null and " +
            "c.parentId is null")
    Slice<Comment> findFeedParentComment(@Param("id") Long id, @Param("type") Long type, Pageable pageable);

    @Query("select c from Comment c where c.commentPk.type =:type and c.parentId = :commentId and c.deletedAt is null")
    Slice<Comment> findFeedChildComment(@Param("type") Long type,@Param("commentId") Long commentId,Pageable pageable);
}
