package org.example.hackathon_test.User.Repository;

import org.example.hackathon_test.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByNameAndEmail(String name, String email);
    Optional<User> findByNameAndEmailAndUsername(String name, String email, String username);
    void deleteByUsername(String username);
}