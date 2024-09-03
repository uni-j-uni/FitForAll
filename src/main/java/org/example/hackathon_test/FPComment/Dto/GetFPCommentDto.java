package org.example.hackathon_test.FPComment.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.FPComment.Entity.FPComment;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetFPCommentDto {
    private Long id;
    private Long freePost_Id;
    private String username;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static GetFPCommentDto from(FPComment fpComment) {
        GetFPCommentDto getFPCommentDto = new GetFPCommentDto();

        getFPCommentDto.setId(fpComment.getId());
        getFPCommentDto.setFreePost_Id(fpComment.getFreePost().getId());
        getFPCommentDto.setUsername(fpComment.getUser().getUsername());
        getFPCommentDto.setContent(fpComment.getContent());
        getFPCommentDto.setCreateDate(fpComment.getCreateDate());
        getFPCommentDto.setModifyDate(fpComment.getModifyDate());

        return getFPCommentDto;
    }
}