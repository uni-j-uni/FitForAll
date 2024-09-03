package org.example.hackathon_test.Review.Repository;

import org.example.hackathon_test.Review.Entity.Review;
import org.example.hackathon_test.Place.Entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByPlaceId(Long id);
}
