package org.example.hackathon_test.CPComment.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.CPComment.Entity.CPComment;

@Getter
@Setter
public class CPCommentDto {
    private String content;

    public static CPCommentDto from(CPComment CPComment) {
        CPCommentDto CPCommentDto = new CPCommentDto();

        CPCommentDto.setContent(CPComment.getContent());

        return CPCommentDto;
    }
}