package org.example.hackathon_test.FreePost.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.FreePost.Entity.FreePost;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetFreePostDto {
    private Long id;
    private String username;
    private String title;
    private String content;
    private Integer totalRecommend;
    private Integer totalNotRecommend;
    private Integer view;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static GetFreePostDto from(FreePost freePost) {
        GetFreePostDto getFreePostDto = new GetFreePostDto();

        getFreePostDto.setId(freePost.getId());
        getFreePostDto.setUsername(freePost.getUser().getUsername());
        getFreePostDto.setTitle(freePost.getTitle());
        getFreePostDto.setContent(freePost.getContent());
        getFreePostDto.setTotalRecommend(freePost.getTotalRecommend());
        getFreePostDto.setTotalNotRecommend(freePost.getTotalNotRecommend());
        getFreePostDto.setView(freePost.getView());
        getFreePostDto.setCreateDate(freePost.getCreateDate());
        getFreePostDto.setModifyDate(freePost.getModifyDate());

        return getFreePostDto;
    }
}