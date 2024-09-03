package org.example.hackathon_test.RPComment.Repository;

import org.example.hackathon_test.RPComment.Entity.RPComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RPCommentRepository extends JpaRepository<RPComment, Long> {
    List<RPComment> findByRecruitPostId(Long rpId);
}