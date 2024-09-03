package org.example.hackathon_test.Calendar.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.Calendar.Entity.Calendar;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetCalendarDto {
    private Long id;
    private Long recruitPostId;
    private String username;
    private String title;
    private String eventTime;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static GetCalendarDto from(Calendar calendar) {
        GetCalendarDto getCalendarDto = new GetCalendarDto();

        getCalendarDto.setId(calendar.getId());
        getCalendarDto.setRecruitPostId(calendar.getRecruitPost().getId());
        getCalendarDto.setUsername(calendar.getUser().getUsername());
        getCalendarDto.setTitle(calendar.getTitle());
        getCalendarDto.setEventTime(calendar.getRecruitPost().getEventTime());
        getCalendarDto.setContent(calendar.getContent());
        getCalendarDto.setCreateDate(calendar.getCreateDate());
        getCalendarDto.setModifyDate(calendar.getModifyDate());

        return getCalendarDto;
    }
}