package org.example.hackathon_test.CPComment.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.CPComment.Dto.CPCommentDto;
import org.example.hackathon_test.CPComment.Entity.CPComment;
import org.example.hackathon_test.CPComment.Repository.CPCommentRepository;
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
public class CPCommentService {
    private final CPCommentRepository cpCommentRepository;
    private final LoginRepository loginRepository;
    private final CoachPostRepository coachPostRepository;

    // Create - Post
    @Transactional
    public void createCPComment(String username, Long coachPostId, CPCommentDto cpCommentDto) {
        User user = loginRepository.findByUsername(username);
        Optional<CoachPost> coachPostOptional = coachPostRepository.findById(coachPostId);
        if (user == null || coachPostOptional.isEmpty()) return;

        CPComment cpComment = new CPComment();
        CoachPost coachPost = coachPostOptional.get();

        cpComment.setUser(user);
        cpComment.setCoachPost(coachPostOptional.get());
        cpComment.setContent(cpCommentDto.getContent());
        coachPost.setIsAnswer(true);

        coachPostRepository.save(coachPost);
        cpCommentRepository.save(cpComment);
    }

    // Read - Get
    public List<CPComment> getAllCPComments() { return cpCommentRepository.findAll(); }
    public Optional<CPComment> getCPCommentById(Long id) { return cpCommentRepository.findById(id); }
    public List<CPComment> getCPCommentByCoachPostId(Long cpId) { return cpCommentRepository.findByCoachPostId(cpId); }

    // Update
    @Transactional
    public CPComment updateCPComment(Long id, CPCommentDto cpCommentDto) {
        Optional<CPComment> cpCommentOptional = cpCommentRepository.findById(id);
        if (cpCommentOptional.isPresent()) {
            CPComment cpComment = cpCommentOptional.get();

            if (cpCommentDto.getContent() != null) cpComment.setContent(cpCommentDto.getContent());

            return cpCommentRepository.save(cpComment);
        }
        else throw new RuntimeException("CPComment not found with id " + id);
    }

    // Delete
    @Transactional
    public void deleteCPComment(String username, Long id) {
        Optional<CPComment> cpCommentOptional = cpCommentRepository.findById(id);
        if (cpCommentOptional.isPresent()) {
            if (cpCommentOptional.get().getUser().getUsername().equals(username)) cpCommentRepository.deleteById(id);
        }
        else throw new RuntimeException("This comment not found with username " + username);
    }
}