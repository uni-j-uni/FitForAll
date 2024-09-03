package org.example.hackathon_test.FPComment.Repository;

import org.example.hackathon_test.FPComment.Entity.FPComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FPCommentRepository extends JpaRepository<FPComment, Long> {
    List<FPComment> findByFreePostId(Long fpId);
}