package org.example.hackathon_test.Calendar.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.Calendar.Dto.CalendarDto;
import org.example.hackathon_test.Calendar.Dto.UpdateMemoDto;
import org.example.hackathon_test.Calendar.Entity.Calendar;
import org.example.hackathon_test.Calendar.Repository.CalendarRepository;
import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import org.example.hackathon_test.RecruitPost.Repository.RecruitPostRepository;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final LoginRepository loginRepository;
    private final RecruitPostRepository recruitPostRepository;

    @Transactional
    public Boolean createCalendar(String username, CalendarDto calendarDto) {
        User user = loginRepository.findByUsername(username);
        Optional<RecruitPost> recruitPostOptional = recruitPostRepository.findById(calendarDto.getRecruitPostId());
        if (user == null || recruitPostOptional.isEmpty()) return false;

        RecruitPost recruitPost = recruitPostOptional.get();
        List<Calendar> calendars = calendarRepository.findByUserUsername(username);
        Calendar calendar = new Calendar();

        if (calendars.contains(calendarRepository.findByRecruitPostIdAndUserUsername(recruitPost.getId(), username)) ||
                recruitPost.getCurrentRecruit().equals(recruitPost.getTotalRecruit())) {
            return false;
        }
        else {
            calendar.setRecruitPost(recruitPost);
            calendar.setUser(user);
            calendar.setTitle(recruitPost.getTitle());
            calendar.setEventTime(recruitPost.getEventTime());
            recruitPost.setCurrentRecruit(recruitPost.getCurrentRecruit() + 1);

            recruitPostRepository.save(recruitPost);
            calendarRepository.save(calendar);

            return true;
        }
    }

    public List<Calendar> getAllCalendars(String username) { return calendarRepository.findAllByUserUsername(username); }
    public List<Calendar> getAllCalendarsByUsername(String username) { return calendarRepository.findByUserUsername(username); }
    public Optional<Calendar> getCalendarById(Long id) { return calendarRepository.findById(id); }
    public Boolean getCalendarByRecruitPostId(Long recruitPostId, String username) {
        Calendar calendar = calendarRepository.findByRecruitPostIdAndUserUsername(recruitPostId, username);
        return calendar != null;
    }

    @Transactional
    public Calendar updateMemo(Long id, UpdateMemoDto updateMemoDto) {
        Optional<Calendar> calendarOptional = calendarRepository.findById(id);

        if (calendarOptional.isPresent()) {
            Calendar calendar = calendarOptional.get();

            if (updateMemoDto.getContent() != null) calendar.setContent(updateMemoDto.getContent());

            return calendarRepository.save(calendar);
        }
        else throw new RuntimeException("Calendar not found with id " + id);
    }

    @Transactional
    public void deleteCalendar(String username, Long recruitPostId) {
        Calendar calendar = calendarRepository.findByRecruitPostIdAndUserUsername(recruitPostId, username);
        if (calendar != null) {
            if (calendar.getUser().getUsername().equals(username)) {
                calendarRepository.deleteByRecruitPostId(recruitPostId);
                Optional<RecruitPost> recruitPostOptional = recruitPostRepository.findById(recruitPostId);
                RecruitPost recruitPost = recruitPostOptional.get();

                recruitPost.setCurrentRecruit(recruitPost.getCurrentRecruit() - 1);
                recruitPostRepository.save(recruitPost);
            }
        }
        else throw new RuntimeException("This post not found with username " + username);
    }
}