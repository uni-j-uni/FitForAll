package org.example.hackathon_test.CoachPost.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.CoachPost.Entity.CoachPost;

@Getter
@Setter
public class CoachPostDto {
    private String content;

    public static CoachPostDto from(CoachPost coachPost) {
        CoachPostDto coachPostDto = new CoachPostDto();

        coachPostDto.setContent(coachPost.getContent());

        return coachPostDto;
    }
}