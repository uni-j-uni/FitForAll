package org.example.hackathon_test.CoachInfo.Repository;

import org.example.hackathon_test.CoachInfo.Entity.CoachInfo;
import org.example.hackathon_test.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoachInfoRepository extends JpaRepository<CoachInfo, Long> {
    Optional<CoachInfo> findByUser(User user);
}
