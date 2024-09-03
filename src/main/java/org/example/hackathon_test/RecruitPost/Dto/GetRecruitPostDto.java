package org.example.hackathon_test.RecruitPost.Dto;

import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetRecruitPostDto {
    private Long id;
    private String username;
    private String title;
    private Integer currentRecruit;
    private Integer totalRecruit;
    private String location;
    private String phone;
    private String eventTime;
    private String content;
    private Integer view;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static GetRecruitPostDto from(RecruitPost recruitPost) {
        GetRecruitPostDto getRecruitPostDto = new GetRecruitPostDto();

        getRecruitPostDto.setId(recruitPost.getId());
        getRecruitPostDto.setUsername(recruitPost.getUser().getUsername());
        getRecruitPostDto.setTitle(recruitPost.getTitle());
        getRecruitPostDto.setCurrentRecruit(recruitPost.getCurrentRecruit());
        getRecruitPostDto.setTotalRecruit(recruitPost.getTotalRecruit());
        getRecruitPostDto.setLocation(recruitPost.getLocation());
        getRecruitPostDto.setPhone(recruitPost.getPhone());
        getRecruitPostDto.setEventTime(recruitPost.getEventTime());
        getRecruitPostDto.setContent(recruitPost.getContent());
        getRecruitPostDto.setView(recruitPost.getView());
        getRecruitPostDto.setCreateDate(recruitPost.getCreateDate());
        getRecruitPostDto.setModifyDate(recruitPost.getModifyDate());

        return getRecruitPostDto;
    }
}