package org.example.hackathon_test.FreePost.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.FreePost.Dto.FreePostDto;
import org.example.hackathon_test.FreePost.Entity.FreePost;
import org.example.hackathon_test.FreePost.Repository.FreePostRepository;
import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class FreePostService {
    private final FreePostRepository freePostRepository;
    private final LoginRepository loginRepository;

    // Create - Post
    @Transactional
    public void createFreePost(String username, FreePostDto freePostDto) {
        User user = loginRepository.findByUsername(username);
        if (user == null) return;

        FreePost freePost = new FreePost();

        freePost.setUser(user);
        freePost.setTitle(freePostDto.getTitle());
        freePost.setContent(freePostDto.getContent());
        freePost.setTotalRecommend(0);
        freePost.setTotalNotRecommend(0);
        freePost.setView(0);

        freePostRepository.save(freePost);
    }

    // 모든 게시물 조회
    public List<FreePost> getAllFreePosts() { return freePostRepository.findAll(); }

    // 특정 게시물에 대한 조회
    @Transactional
    public Optional<FreePost> getFreePostById(Long id) {
        Optional<FreePost> freePostOptional = freePostRepository.findById(id);
        FreePost freePost = freePostOptional.get();

        freePost.setView(freePost.getView() + 1);
        freePostRepository.save(freePost);

        return freePostRepository.findById(id);
    }

    // Update
    @Transactional
    public FreePost updateFreePost(String username, Long id, FreePostDto freePostDto) {
        Optional<FreePost> freePostOptional = freePostRepository.findById(id);
        if (freePostOptional.isPresent()) {
            if (freePostOptional.get().getUser().getUsername().equals(username)) {
                FreePost freePost = freePostOptional.get();

                if (freePostDto.getTitle() != null) freePost.setTitle(freePostDto.getTitle());
                if (freePostDto.getContent() != null) freePost.setContent(freePostDto.getContent());

                return freePostRepository.save(freePost);
            }
            else throw new RuntimeException("Not found username" + username);
        }
        else throw new RuntimeException("FreePost not found with id " + id);
    }

    // Delete
    @Transactional
    public void deleteFreePost(String username, Long id) {
        Optional<FreePost> freePostOptional = freePostRepository.findById(id);
        if (freePostOptional.isPresent()) {
            if (freePostOptional.get().getUser().getUsername().equals(username)) freePostRepository.deleteById(id);
        }
        else throw new RuntimeException("This post not found with username " + username);
    }
}