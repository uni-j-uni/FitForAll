package org.example.hackathon_test.Review.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.Review.Entity.Review;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetReviewDto {
    private Long id;
    private Long placeId;
    private String username;
    private String content;
    private Integer starRating;
    private LocalDateTime createDate;

    public static GetReviewDto from(Review review){
        GetReviewDto getReviewDto = new GetReviewDto();

        getReviewDto.setId(review.getId());
        getReviewDto.setPlaceId(review.getPlace().getId());
        getReviewDto.setUsername(review.getUser().getUsername());
        getReviewDto.setContent(review.getContent());
        getReviewDto.setStarRating(review.getStarRating());
        getReviewDto.setCreateDate(review.getCreateDate());

        return getReviewDto;
    }
}
