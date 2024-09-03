package org.example.hackathon_test.CoachPost.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.CoachPost.Entity.CoachPost;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetCoachPostDto {
    private Long id;
    private String username;
    private String content;
    private Boolean isAnswer;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static GetCoachPostDto from(CoachPost coachPost) {
        GetCoachPostDto getCoachPostDto = new GetCoachPostDto();

        getCoachPostDto.setId(coachPost.getId());
        getCoachPostDto.setUsername(coachPost.getUser().getUsername());
        getCoachPostDto.setContent(coachPost.getContent());
        getCoachPostDto.setIsAnswer(coachPost.getIsAnswer());
        getCoachPostDto.setCreateDate(coachPost.getCreateDate());
        getCoachPostDto.setModifyDate(coachPost.getModifyDate());

        return getCoachPostDto;
    }
}