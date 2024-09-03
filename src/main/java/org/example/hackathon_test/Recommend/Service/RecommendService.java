package org.example.hackathon_test.Recommend.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.FreePost.Entity.FreePost;
import org.example.hackathon_test.FreePost.Repository.FreePostRepository;
import org.example.hackathon_test.Recommend.Dto.RecommendDto;
import org.example.hackathon_test.Recommend.Entity.Recommend;
import org.example.hackathon_test.Recommend.Repository.RecommendRepository;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class RecommendService {
    private final RecommendRepository recommendRepository;
    private final LoginRepository loginRepository;
    private final FreePostRepository freePostRepository;

    // Create - Post
    @Transactional
    public String createRecommend(String username, Long freePostId, RecommendDto recommendDto) {
        User user = loginRepository.findByUsername(username);
        Optional<FreePost> freePostOptional = freePostRepository.findById(freePostId);
        Optional<Recommend> recommendOptional = recommendRepository.findByFreePostId(freePostId);
        if (user == null || freePostOptional.isEmpty() || recommendOptional.isPresent()) return null;

        Recommend recommend = new Recommend();
        FreePost freePost = freePostOptional.get();

        recommend.setUser(user);
        recommend.setFreePost(freePost);

        if (recommendDto.getRecommendType().equals("1")) {
            recommend.setRecommendType("1");
            recommend.setIsRecommend(true);
            freePost.setTotalRecommend(freePost.getTotalRecommend() + 1);
            System.out.println(freePost.getTotalRecommend());
        }
        else if (recommendDto.getRecommendType().equals("-1")) {
            recommend.setRecommendType("-1");
            recommend.setIsRecommend(true);
            freePost.setTotalNotRecommend(freePost.getTotalNotRecommend() + 1);
            System.out.println(freePost.getTotalNotRecommend());
        }
        else recommend.setRecommendType("0");

        System.out.println(recommendDto.getRecommendType());
        recommendRepository.save(recommend);
        freePostRepository.save(freePost);

        return recommend.getRecommendType();
    }
}