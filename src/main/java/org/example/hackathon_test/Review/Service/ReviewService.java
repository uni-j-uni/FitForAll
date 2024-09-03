package org.example.hackathon_test.Review.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.RPComment.Entity.RPComment;
import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import org.example.hackathon_test.Review.Dto.ReviewDto;
import org.example.hackathon_test.Review.Entity.Review;
import org.example.hackathon_test.Review.Repository.ReviewRepository;
import org.example.hackathon_test.Place.Entity.Place;
import org.example.hackathon_test.Place.Repository.PlaceRepository;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewService {
    private final PlaceRepository placeRepository;
    private final LoginRepository loginRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void createReview(String username, Long placeId, ReviewDto reviewDto){
        User user = loginRepository.findByUsername(username);
        Optional<Place> placeOptional = placeRepository.findById(placeId);
        if (user == null || placeOptional.isEmpty()) return;

        Review review = new Review();

        review.setUser(user);
        review.setPlace(placeOptional.get());
        review.setContent(reviewDto.getContent());
        review.setStarRating(reviewDto.getStarRating());

        reviewRepository.save(review);
    }

    public Optional<Review> getReviewById(Long id) { return reviewRepository.findById(id); }
    public List<Review> getReviewByPlaceId(Long placeId) { return reviewRepository.findByPlaceId(placeId); }
}
