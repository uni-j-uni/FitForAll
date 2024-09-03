package org.example.hackathon_test.FreePost.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.FreePost.Entity.FreePost;

@Getter
@Setter
public class FreePostDto {
    private String title;
    private String content;

    public static FreePostDto from(FreePost freePost) {
        FreePostDto freePostDto = new FreePostDto();

        freePostDto.setTitle(freePost.getTitle());
        freePostDto.setContent(freePost.getContent());

        return freePostDto;
    }
}