package org.example.hackathon_test.Recommend.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.Recommend.Dto.RecommendDto;
import org.example.hackathon_test.Recommend.Service.RecommendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/recommend")
public class RecommendController {
    private final RecommendService RecommendService;

    // 장소모집 게시물 생성
    @PostMapping("/{freePostId}")
    public String createRecommend(Authentication authentication, @PathVariable(name="freePostId") Long freePostId, @RequestBody RecommendDto recommendDto) {
        return RecommendService.createRecommend(authentication.getName(), freePostId, recommendDto);
    }
}