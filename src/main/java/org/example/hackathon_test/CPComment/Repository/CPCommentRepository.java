package org.example.hackathon_test.CPComment.Repository;

import org.example.hackathon_test.CPComment.Entity.CPComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CPCommentRepository extends JpaRepository<CPComment, Long> {
    List<CPComment> findByCoachPostId(Long cpId);
}