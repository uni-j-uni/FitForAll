package org.example.hackathon_test.RecruitPost.Dto;

import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitPostDto {
    private String title;
    private Integer totalRecruit;
    private String location;
    private String phone;
    private String eventTime;
    private String content;

    public static RecruitPostDto from(RecruitPost recruitPost) {
        RecruitPostDto recruitPostDto = new RecruitPostDto();

        recruitPostDto.setTitle(recruitPost.getTitle());
        recruitPostDto.setTotalRecruit(recruitPost.getTotalRecruit());
        recruitPostDto.setLocation(recruitPost.getLocation());
        recruitPostDto.setPhone(recruitPost.getPhone());
        recruitPostDto.setEventTime(recruitPost.getEventTime());
        recruitPostDto.setContent(recruitPost.getContent());

        return recruitPostDto;
    }
}