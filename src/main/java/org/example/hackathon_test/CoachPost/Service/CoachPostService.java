package org.example.hackathon_test.CoachPost.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.CoachPost.Dto.CoachPostDto;
import org.example.hackathon_test.CoachPost.Entity.CoachPost;
import org.example.hackathon_test.CoachPost.Repository.CoachPostRepository;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class CoachPostService {
    private final CoachPostRepository coachPostRepository;
    private final LoginRepository loginRepository;

    // Create - Post
    @Transactional
    public Long createCoachPost(String username, CoachPostDto coachPostDto) {
        User user = loginRepository.findByUsername(username);
        if (user == null) return null;

        CoachPost coachPost = new CoachPost();

        coachPost.setUser(user);
        coachPost.setIsAnswer(false);
        coachPost.setContent(coachPostDto.getContent());

        coachPostRepository.save(coachPost);
        return coachPost.getId();
    }

    // 모든 게시물 조회
    public List<CoachPost> getAllCoachPosts() { return coachPostRepository.findAll(); }
    public List<CoachPost> getAllCoachPostsByUsername(String username) { return coachPostRepository.findCoachPostsByUserUsername(username); }

    // 특정 게시물에 대한 조회
    @Transactional
    public Optional<CoachPost> getCoachPostById(Long id) { return coachPostRepository.findById(id); }

    // Update
    @Transactional
    public CoachPost updateCoachPost(String username, Long id, CoachPostDto coachPostDto) {
        Optional<CoachPost> coachPostOptional = coachPostRepository.findById(id);
        if (coachPostOptional.isPresent()) {
            if (coachPostOptional.get().getUser().getUsername().equals(username)) {
                CoachPost coachPost = coachPostOptional.get();

                if (coachPostDto.getContent() != null) coachPost.setContent(coachPostDto.getContent());

                return coachPostRepository.save(coachPost);
            }
            else throw new RuntimeException("Not found username" + username);
        }
        else throw new RuntimeException("FreePost not found with id " + id);
    }

    // Delete
    @Transactional
    public void deleteFreePost(String username, Long id) {
        Optional<CoachPost> coachPostOptional = coachPostRepository.findById(id);
        if (coachPostOptional.isPresent()) {
            if (coachPostOptional.get().getUser().getUsername().equals(username)) coachPostRepository.deleteById(id);
        }
        else throw new RuntimeException("This post not found with username " + username);
    }
}