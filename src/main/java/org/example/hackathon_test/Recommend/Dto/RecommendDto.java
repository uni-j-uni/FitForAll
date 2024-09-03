package org.example.hackathon_test.Recommend.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.Recommend.Entity.Recommend;

@Getter
@Setter
public class RecommendDto {
    private String recommendType;

    public static RecommendDto from(Recommend recommend) {
        RecommendDto recommendDto = new RecommendDto();

        recommendDto.setRecommendType(recommend.getRecommendType());

        return recommendDto;
    }
}