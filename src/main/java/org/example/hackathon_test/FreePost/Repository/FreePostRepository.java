package org.example.hackathon_test.FreePost.Repository;

import org.example.hackathon_test.FreePost.Entity.FreePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreePostRepository extends JpaRepository<FreePost, Long> {
}