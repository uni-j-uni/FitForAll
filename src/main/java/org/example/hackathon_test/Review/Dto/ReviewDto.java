package org.example.hackathon_test.Review.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.Review.Entity.Review;

@Getter
@Setter
public class ReviewDto {
    private String content;
    private Integer starRating;

    public static ReviewDto from(Review review){
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setContent(review.getContent());
        reviewDto.setStarRating(review.getStarRating());

        return reviewDto;
    }
}
