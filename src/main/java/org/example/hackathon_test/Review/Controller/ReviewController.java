package org.example.hackathon_test.Review.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.RPComment.Dto.GetRPCommentDto;
import org.example.hackathon_test.RPComment.Dto.RPCommentDto;
import org.example.hackathon_test.RPComment.Entity.RPComment;
import org.example.hackathon_test.Review.Dto.GetReviewDto;
import org.example.hackathon_test.Review.Dto.ReviewDto;
import org.example.hackathon_test.Review.Entity.Review;
import org.example.hackathon_test.Review.Service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{placeId}")
    public ResponseEntity<Void> createReview(Authentication authentication, @PathVariable(name="placeId") Long placeId, @RequestBody ReviewDto reviewDto) {
        reviewService.createReview(authentication.getName(), placeId, reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetReviewDto> getReviewById(@PathVariable(name="id") Long id) {
        Optional<Review> reviewOptional = reviewService.getReviewById(id);
        if (reviewOptional.isPresent()) {
            GetReviewDto dto = GetReviewDto.from(reviewOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/place/{placeId}")
    public ResponseEntity<List<GetReviewDto>> getReviewsByPlaceId(@PathVariable(name="placeId") Long placeId) {
        List<Review> reviews = reviewService.getReviewByPlaceId(placeId);
        List<GetReviewDto> getReviewDtos = new ArrayList<>();

        for (Review review : reviews) {
            GetReviewDto dto = GetReviewDto.from(review);
            getReviewDtos.add(dto);
        }
        return ResponseEntity.ok(getReviewDtos);
    }
}
