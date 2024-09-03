package org.example.hackathon_test.Calendar.Repository;

import org.example.hackathon_test.Calendar.Entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByUserUsername(String username);
    List<Calendar> findByUserUsername(String username);
    List<Calendar> findByRecruitPostId(Long recruitPostId);
    Calendar findByRecruitPostIdAndUserUsername(Long recruitPostId, String username);
    void deleteByRecruitPostId(Long recruitPostId);
}