package org.example.hackathon_test.Calendar.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.Calendar.Dto.CalendarDto;
import org.example.hackathon_test.Calendar.Dto.GetCalendarDto;
import org.example.hackathon_test.Calendar.Dto.UpdateMemoDto;
import org.example.hackathon_test.Calendar.Entity.Calendar;
import org.example.hackathon_test.Calendar.Service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping
    public Boolean createCalendar(Authentication authentication, @RequestBody CalendarDto calendarDto) {
        return calendarService.createCalendar(authentication.getName(), calendarDto);
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllCalendars(Authentication authentication) {
        List<Calendar> calendars = calendarService.getAllCalendars(authentication.getName());
        List<String> eventTimeList = new ArrayList<>();

        for (Calendar calendar : calendars) {
            eventTimeList.add(calendar.getEventTime());
        }

        return ResponseEntity.ok(eventTimeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCalendarDto> getCalendarById(@PathVariable(name="id") Long id) {
        Optional<Calendar> calendarOptional = calendarService.getCalendarById(id);
        if (calendarOptional.isPresent()) {
            GetCalendarDto dto = GetCalendarDto.from(calendarOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/eventTime/{eventTime}")
    public ResponseEntity<List<GetCalendarDto>> getAllCalendarsByUsername(Authentication authentication, @PathVariable(name="eventTime") String eventTime) {
        List<Calendar> calendars = calendarService.getAllCalendarsByUsername(authentication.getName());
        List<GetCalendarDto> getCalendarDtos = new ArrayList<>();

        for (Calendar calendar : calendars) {
            GetCalendarDto dto = GetCalendarDto.from(calendar);
            if (dto.getEventTime().startsWith(eventTime)) getCalendarDtos.add(dto);
        }

        return ResponseEntity.ok(getCalendarDtos);
    }

    @GetMapping("/recruitPost/{recruitPostId}")
    public Boolean getRecruitPostById(Authentication authentication, @PathVariable(name="recruitPostId") Long recruitPostId) {
        return calendarService.getCalendarByRecruitPostId(recruitPostId, authentication.getName());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateMemoDto> updateMemo(@PathVariable(name="id") Long id, @RequestBody UpdateMemoDto updateMemoDto) {
        try {
            Calendar updatedCalendar = calendarService.updateMemo(id, updateMemoDto);
            return ResponseEntity.ok(UpdateMemoDto.from(updatedCalendar));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{recruitPostId}")
    public ResponseEntity<Void> deleteCalendar(Authentication authentication, @PathVariable(name="recruitPostId") Long recruitPostId) {
        calendarService.deleteCalendar(authentication.getName(), recruitPostId);
        return ResponseEntity.noContent().build();
    }
}