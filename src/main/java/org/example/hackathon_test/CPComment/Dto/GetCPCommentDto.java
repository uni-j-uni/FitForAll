package org.example.hackathon_test.CPComment.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.CPComment.Entity.CPComment;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetCPCommentDto {
    private Long id;
    private Long coachPost_Id;
    private String username;
    private String name;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static GetCPCommentDto from(CPComment cpComment) {
        GetCPCommentDto getCPCommentDto = new GetCPCommentDto();

        getCPCommentDto.setId(cpComment.getId());
        getCPCommentDto.setCoachPost_Id(cpComment.getCoachPost().getId());
        getCPCommentDto.setUsername(cpComment.getUser().getUsername());
        getCPCommentDto.setName(cpComment.getUser().getName());
        getCPCommentDto.setContent(cpComment.getContent());
        getCPCommentDto.setCreateDate(cpComment.getCreateDate());
        getCPCommentDto.setModifyDate(cpComment.getModifyDate());

        return getCPCommentDto;
    }
}