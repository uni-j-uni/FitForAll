package org.example.hackathon_test.Calendar.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.Calendar.Entity.Calendar;

@Getter
@Setter
public class UpdateMemoDto {
    private String content;

    public static UpdateMemoDto from(Calendar calendar) {
        UpdateMemoDto updateMemoDto = new UpdateMemoDto();

        updateMemoDto.setContent(calendar.getContent());

        return updateMemoDto;
    }
}