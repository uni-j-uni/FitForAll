package org.example.hackathon_test.RecruitPost.Repository;

import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitPostRepository extends JpaRepository<RecruitPost, Long> {
}