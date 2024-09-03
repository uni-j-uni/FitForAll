package org.example.hackathon_test.User.Repository;

import org.example.hackathon_test.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
