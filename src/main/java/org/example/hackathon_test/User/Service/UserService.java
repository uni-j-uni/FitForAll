package org.example.hackathon_test.User.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.Calendar.Entity.Calendar;
import org.example.hackathon_test.Calendar.Repository.CalendarRepository;
import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import org.example.hackathon_test.RecruitPost.Repository.RecruitPostRepository;
import org.example.hackathon_test.User.Dto.UpdatePasswordDto;
import org.example.hackathon_test.User.Dto.CreateUserDto;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserService{
    private final UserRepository userRepository;
    private final RecruitPostRepository recruitPostRepository;
    private final CalendarRepository calendarRepository;
    private final PasswordEncoder passwordEncoder;

    // Create - Post
    @Transactional
    public void createUser(CreateUserDto createUserDto) {
        User user = new User();

        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setUsername(createUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        userRepository.save(user);
    }

    public Boolean isFirstLogin(String username) { return userRepository.findByUsername(username).get().getIsFirst(); }

    public Boolean isCoach(String username) { return userRepository.findByUsername(username).get().getIsCoach(); }

    public String findUsername(String name, String email) {
        Optional<User> userOptional = userRepository.findByNameAndEmail(name, email);
        if (userOptional.isPresent()) return userOptional.get().getUsername();
        else return null;
    }

    // Read - Get
    public List<User> getAllUsers() { return userRepository.findAll(); }
    public Optional<User> getUserByUsername(String username) { return userRepository.findByUsername(username); }

    // Update - Patch
    @Transactional
    public User updateUser(String username, UpdatePasswordDto updatePasswordDto) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(updatePasswordDto.getCurrentPassword(), user.getPassword()) && updatePasswordDto.getNewPassword() != null) {
                user.setPassword(passwordEncoder.encode(updatePasswordDto.getNewPassword()));
                return userRepository.save(user);
            }
            else throw new RuntimeException("Current password is incorrect");
        }
        else throw new RuntimeException("User not found with username " + username);
    }

    // Delete
    @Transactional
    public void deleteUser(String username){
        List<Calendar> calendars = calendarRepository.findByUserUsername(username);

        for (Calendar calendar : calendars) {
            Long recruitPostId = calendar.getRecruitPost().getId();
            Optional<RecruitPost> recruitPostOptional = recruitPostRepository.findById(recruitPostId);
            RecruitPost recruitPost = recruitPostOptional.get();

            recruitPost.setCurrentRecruit(recruitPost.getCurrentRecruit() - 1);
            recruitPostRepository.save(recruitPost);
        }
        userRepository.deleteByUsername(username);
    }
}