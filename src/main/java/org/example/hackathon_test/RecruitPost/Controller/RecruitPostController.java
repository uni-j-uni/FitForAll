package org.example.hackathon_test.RecruitPost.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.RecruitPost.Dto.GetRecruitPostDto;
import org.example.hackathon_test.RecruitPost.Entity.RecruitPost;
import org.example.hackathon_test.RecruitPost.Dto.RecruitPostDto;
import org.example.hackathon_test.RecruitPost.Service.RecruitPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/recruit")
public class RecruitPostController {
    private final RecruitPostService recruitPostService;

    // 장소모집 게시물 생성
    @PostMapping
    public ResponseEntity<Long> createRecruitPost(Authentication authentication, @RequestBody RecruitPostDto recruitPostDto) {
        Long id = recruitPostService.createRecruitPost(authentication.getName(), recruitPostDto);
        return ResponseEntity.ok(id);
    }

    // 모든 장소모집 게시물 조회
    @GetMapping
    public ResponseEntity<List<GetRecruitPostDto>> getAllRecruitPosts() {
        List<RecruitPost> recruitPosts = recruitPostService.getAllRecruitPosts();
        List<GetRecruitPostDto> recruitPostDtos = new ArrayList<>();
        for (RecruitPost recruitPost : recruitPosts) {
            GetRecruitPostDto dto = GetRecruitPostDto.from(recruitPost);
            recruitPostDtos.add(dto);
        }
        return ResponseEntity.ok(recruitPostDtos);
    }

    // 장소모집 게시물 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetRecruitPostDto> getRecruitPostById(@PathVariable(name="id") Long id) {
        Optional<RecruitPost> recruitPostOptional = recruitPostService.getRecruitPostById(id);
        if (recruitPostOptional.isPresent()) {
            GetRecruitPostDto dto = GetRecruitPostDto.from(recruitPostOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    // 장소모집 게시물 날짜로 조회
    @GetMapping("/eventTime/{eventTime}")
    public ResponseEntity<List<GetRecruitPostDto>> getRecruitPostsByEventTime(@PathVariable(name="eventTime") String eventTime) {
        List<RecruitPost> recruitPosts = recruitPostService.getAllRecruitPosts();
        List<GetRecruitPostDto> recruitPostDtos = new ArrayList<>();

        for (RecruitPost recruitPost : recruitPosts) {
            GetRecruitPostDto dto = GetRecruitPostDto.from(recruitPost);
            if (dto.getEventTime().startsWith(eventTime)) recruitPostDtos.add(dto);
        }
        return ResponseEntity.ok(recruitPostDtos);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RecruitPostDto> updateRecruitPost(Authentication authentication, @PathVariable(name="id") Long id, @RequestBody RecruitPostDto recruitPostDto) {
        try {
            RecruitPost updatedRecruitPost = recruitPostService.updateRecruitPost(authentication.getName(), id, recruitPostDto);
            return ResponseEntity.ok(RecruitPostDto.from(updatedRecruitPost));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruitPost(Authentication authentication, @PathVariable(name="id") Long id) {
        recruitPostService.deleteRecruitPost(authentication.getName(), id);

        return ResponseEntity.noContent().build();
    }
}