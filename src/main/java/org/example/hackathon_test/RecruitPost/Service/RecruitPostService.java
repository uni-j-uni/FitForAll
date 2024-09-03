package org.example.hackathon_test.RecruitPost.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.Calendar.Entity.Calendar;
import org.example.hackathon_test.Calendar.Repository.CalendarRepository;
import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.RecruitPost.Dto.RecruitPostDto;
import org.example.hackathon_test.RecruitPost.Repository.RecruitPostRepository;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class RecruitPostService {
    private final RecruitPostRepository recruitPostRepository;
    private final CalendarRepository calendarRepository;
    private final LoginRepository loginRepository;

    // Create - Post
    @Transactional
    public Long createRecruitPost(String username, RecruitPostDto recruitPostDto) {
        User user = loginRepository.findByUsername(username);
        if (user == null) return null;

        RecruitPost recruitPost = new RecruitPost();

        recruitPost.setUser(user);
        recruitPost.setTitle(recruitPostDto.getTitle());
        recruitPost.setCurrentRecruit(0);
        recruitPost.setTotalRecruit(recruitPostDto.getTotalRecruit());
        recruitPost.setLocation(recruitPostDto.getLocation());
        recruitPost.setPhone(recruitPostDto.getPhone());
        recruitPost.setEventTime(recruitPostDto.getEventTime());
        recruitPost.setContent(recruitPostDto.getContent());
        recruitPost.setView(0);

        recruitPostRepository.save(recruitPost);

        return recruitPost.getId();
    }

    // Read - Get
    public List<RecruitPost> getAllRecruitPosts() { return recruitPostRepository.findAll(); }
    @Transactional
    public Optional<RecruitPost> getRecruitPostById(Long id) {
        Optional<RecruitPost> recruitPostOptional = recruitPostRepository.findById(id);
        RecruitPost recruitPost = recruitPostOptional.get();

        recruitPost.setView(recruitPost.getView() + 1);
        recruitPostRepository.save(recruitPost);

        return recruitPostRepository.findById(id);
    }

    // Update
    @Transactional
    public RecruitPost updateRecruitPost(String username, Long id, RecruitPostDto recruitPostDto) {
        Optional<RecruitPost> postOptional = recruitPostRepository.findById(id);
        if (postOptional.isPresent()) {
            if (postOptional.get().getUser().getUsername().equals(username)) {
                RecruitPost recruitPost = postOptional.get();

                if (recruitPostDto.getTitle() != null) recruitPost.setTitle(recruitPostDto.getTitle());
                if (recruitPostDto.getTotalRecruit() != null) recruitPost.setTotalRecruit(recruitPostDto.getTotalRecruit());
                if (recruitPostDto.getLocation() != null) recruitPost.setLocation(recruitPostDto.getLocation());
                if (recruitPostDto.getPhone() != null) recruitPost.setPhone(recruitPostDto.getPhone());
                if (recruitPostDto.getEventTime() != null) {
                    recruitPost.setEventTime(recruitPostDto.getEventTime());

                    List<Calendar> calendars = calendarRepository.findByRecruitPostId(id);
                    for (Calendar calendar : calendars) {
                        calendar.setEventTime(recruitPostDto.getEventTime());
                        calendarRepository.save(calendar);
                    }
                }
                if (recruitPostDto.getContent() != null) recruitPost.setContent(recruitPostDto.getContent());

                return recruitPostRepository.save(recruitPost);
            }
            else throw new RuntimeException("Not found username" + username);
        }
        else throw new RuntimeException("RecruitPost not found with id " + id);
    }

    // Delete
    @Transactional
    public void deleteRecruitPost(String username, Long id) {
        Optional<RecruitPost> postOptional = recruitPostRepository.findById(id);
        if (postOptional.get().getUser().getUsername().equals(username)) recruitPostRepository.deleteById(id);
        else throw new RuntimeException("This post not found with username " + username);
    }
}