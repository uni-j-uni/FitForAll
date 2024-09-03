package org.example.hackathon_test.RPComment.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.RPComment.Dto.RPCommentDto;
import org.example.hackathon_test.RPComment.Entity.RPComment;
import org.example.hackathon_test.RPComment.Repository.RPCommentRepository;
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
public class RPCommentService {
    private final RPCommentRepository rpCommentRepository;
    private final RecruitPostRepository recruitPostRepository;
    private final LoginRepository loginRepository;

    // Create - Post
    @Transactional
    public void createRPComment(String username, Long recruitPostId, RPCommentDto RPCommentDto) {
        User user = loginRepository.findByUsername(username);
        Optional<RecruitPost> recruitPostOptional = recruitPostRepository.findById(recruitPostId);
        if (user == null || recruitPostOptional.isEmpty()) return;

        RPComment rpComment = new RPComment();

        rpComment.setUser(user);
        rpComment.setRecruitPost(recruitPostOptional.get());
        rpComment.setContent(RPCommentDto.getContent());

        rpCommentRepository.save(rpComment);
    }

    // Read - Get
    public Optional<RPComment> getRPCommentById(Long id) { return rpCommentRepository.findById(id); }
    public List<RPComment> getRPCommentByRecruitPostId(Long rpId) { return rpCommentRepository.findByRecruitPostId(rpId); }

    // Update
    @Transactional
    public RPComment updateRPComment(Long id, RPCommentDto RPCommentDto) {
        Optional<RPComment> postOptional = rpCommentRepository.findById(id);
        if (postOptional.isPresent()) {
            RPComment rpComment = postOptional.get();

            if (RPCommentDto.getContent() != null) rpComment.setContent(RPCommentDto.getContent());

            return rpCommentRepository.save(rpComment);
        }
        else throw new RuntimeException("RPComment not found with id " + id);
    }

    // Delete
    @Transactional
    public void deleteRPComment(String username, Long id) {
        Optional<RPComment> postOptional = rpCommentRepository.findById(id);
        if (postOptional.isPresent()) {
            if (postOptional.get().getUser().getUsername().equals(username)) rpCommentRepository.deleteById(id);
        }
        else throw new RuntimeException("This post not found with username " + username);
    }
}