package org.example.hackathon_test.UserInfo.Repository;

import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.UserInfo.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUser(User user);
    List<UserInfo> findByNickname(String nickname);

}
