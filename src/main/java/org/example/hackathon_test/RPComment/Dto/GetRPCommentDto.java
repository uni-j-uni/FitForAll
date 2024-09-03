package org.example.hackathon_test.RPComment.Dto;

import org.example.hackathon_test.RPComment.Entity.RPComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetRPCommentDto {
    private Long id;
    private Long recruitPost_Id;
    private String username;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static GetRPCommentDto from(RPComment rpComment) {
        GetRPCommentDto getRPCommentDto = new GetRPCommentDto();

        getRPCommentDto.setId(rpComment.getId());
        getRPCommentDto.setRecruitPost_Id(rpComment.getRecruitPost().getId());
        getRPCommentDto.setUsername(rpComment.getUser().getUsername());
        getRPCommentDto.setContent(rpComment.getContent());
        getRPCommentDto.setCreateDate(rpComment.getCreateDate());
        getRPCommentDto.setModifyDate(rpComment.getModifyDate());

        return getRPCommentDto;
    }
}