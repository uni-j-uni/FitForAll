package org.example.hackathon_test.FPComment.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.FPComment.Dto.FPCommentDto;
import org.example.hackathon_test.FPComment.Entity.FPComment;
import org.example.hackathon_test.FPComment.Repository.FPCommentRepository;
import org.example.hackathon_test.FreePost.Entity.FreePost;
import org.example.hackathon_test.FreePost.Repository.FreePostRepository;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class FPCommentService {
    private final FPCommentRepository fpCommentRepository;
    private final LoginRepository loginRepository;
    private final FreePostRepository freePostRepository;

    // Create - Post
    @Transactional
    public void createFPComment(String username, Long freePostId, FPCommentDto fpCommentDto) {
        User user = loginRepository.findByUsername(username);
        Optional<FreePost> freePostOptional = freePostRepository.findById(freePostId);
        if (user == null || freePostOptional.isEmpty()) return;

        FPComment fpComment = new FPComment();

        fpComment.setUser(user);
        fpComment.setFreePost(freePostOptional.get());
        fpComment.setContent(fpCommentDto.getContent());

        fpCommentRepository.save(fpComment);
    }

    // Read - Get
    public List<FPComment> getAllFPComments() { return fpCommentRepository.findAll(); }
    public Optional<FPComment> getFPCommentById(Long id) { return fpCommentRepository.findById(id); }
    public List<FPComment> getFPCommentByFreePostId(Long fpId) { return fpCommentRepository.findByFreePostId(fpId); }

    // Update
    @Transactional
    public FPComment updateFPComment(Long id, FPCommentDto fpCommentDto) {
        Optional<FPComment> fpCommentOptional = fpCommentRepository.findById(id);
        if (fpCommentOptional.isPresent()) {
            FPComment fpComment = fpCommentOptional.get();

            if (fpCommentDto.getContent() != null) fpComment.setContent(fpCommentDto.getContent());

            return fpCommentRepository.save(fpComment);
        }
        else throw new RuntimeException("FPComment not found with id " + id);
    }

    // Delete
    @Transactional
    public void deleteFPComment(String username, Long id) {
        Optional<FPComment> fpCommentOptional = fpCommentRepository.findById(id);
        if (fpCommentOptional.isPresent()) {
            if (fpCommentOptional.get().getUser().getUsername().equals(username)) fpCommentRepository.deleteById(id);
        }
        else throw new RuntimeException("This comment not found with username " + username);
    }
}