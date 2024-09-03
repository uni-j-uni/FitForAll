package org.example.hackathon_test.Calendar.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.Calendar.Entity.Calendar;

@Getter
@Setter
public class CalendarDto {
    private Long recruitPostId;

    public static CalendarDto from(Calendar calendar) {
        CalendarDto calendarDto = new CalendarDto();

        calendarDto.setRecruitPostId(calendar.getRecruitPost().getId());

        return calendarDto;
    }
}