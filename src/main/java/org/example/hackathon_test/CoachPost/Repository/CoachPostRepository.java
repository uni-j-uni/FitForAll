package org.example.hackathon_test.CoachPost.Repository;

import org.example.hackathon_test.CoachPost.Entity.CoachPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachPostRepository extends JpaRepository<CoachPost, Long> {
    List<CoachPost> findCoachPostsByUserUsername(String username);
}