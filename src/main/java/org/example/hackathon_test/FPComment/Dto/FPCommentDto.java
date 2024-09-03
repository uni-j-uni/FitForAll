package org.example.hackathon_test.FPComment.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.FPComment.Entity.FPComment;

@Getter
@Setter
public class FPCommentDto {
    private String content;

    public static FPCommentDto from(FPComment fpComment) {
        FPCommentDto fpCommentDto = new FPCommentDto();

        fpCommentDto.setContent(fpComment.getContent());

        return fpCommentDto;
    }
}