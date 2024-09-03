package org.example.hackathon_test.RPComment.Dto;

import org.example.hackathon_test.RPComment.Entity.RPComment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RPCommentDto {
    private String content;

    public static RPCommentDto from(RPComment rpComment) {
        RPCommentDto RPCommentDto = new RPCommentDto();

        RPCommentDto.setContent(rpComment.getContent());

        return RPCommentDto;
    }
}