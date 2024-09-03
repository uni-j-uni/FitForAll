package org.example.hackathon_test.Recommend.Repository;

import org.example.hackathon_test.Recommend.Entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    Optional<Recommend> findByFreePostId(Long fpId);
}