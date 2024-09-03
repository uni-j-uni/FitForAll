package org.example.hackathon_test.CoachPost.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.CoachPost.Dto.CoachPostDto;
import org.example.hackathon_test.CoachPost.Dto.GetCoachPostDto;
import org.example.hackathon_test.CoachPost.Entity.CoachPost;
import org.example.hackathon_test.CoachPost.Service.CoachPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/coach")
public class CoachPostController {
    private final CoachPostService coachPostService;

    @PostMapping
    public ResponseEntity<Long> createCoachPost(Authentication authentication, @RequestBody CoachPostDto coachPostDto) {
        Long id = coachPostService.createCoachPost(authentication.getName(), coachPostDto);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<List<GetCoachPostDto>> getAllCoachPosts() {
        List<CoachPost> coachPosts = coachPostService.getAllCoachPosts();
        List<GetCoachPostDto> getCoachPostDtos = new ArrayList<>();
        for (CoachPost coachPost : coachPosts) {
            GetCoachPostDto dto = GetCoachPostDto.from(coachPost);
            getCoachPostDtos.add(dto);
        }
        return ResponseEntity.ok(getCoachPostDtos);
    }

    @GetMapping("/username")
    public ResponseEntity<List<GetCoachPostDto>> getAllCoachPostsByUsername(Authentication authentication) {
        List<CoachPost> coachPosts = coachPostService.getAllCoachPostsByUsername(authentication.getName());
        List<GetCoachPostDto> getCoachPostDtos = new ArrayList<>();
        for (CoachPost coachPost : coachPosts) {
            GetCoachPostDto dto = GetCoachPostDto.from(coachPost);
            getCoachPostDtos.add(dto);
        }
        return ResponseEntity.ok(getCoachPostDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCoachPostDto> getCoachPostById(@PathVariable(name="id") Long id) {
        Optional<CoachPost> freePostOptional = coachPostService.getCoachPostById(id);
        if (freePostOptional.isPresent()) {
            GetCoachPostDto dto = GetCoachPostDto.from(freePostOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    // Update - Patch
    @PatchMapping("/{id}")
    public ResponseEntity<CoachPostDto> updateCoachPost(Authentication authentication, @PathVariable(name="id") Long id, @RequestBody CoachPostDto coachPostDto) {
        try {
            CoachPost updatedCoachPost = coachPostService.updateCoachPost(authentication.getName(), id, coachPostDto);
            return ResponseEntity.ok(CoachPostDto.from(updatedCoachPost));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoachPost(Authentication authentication, @PathVariable(name="id") Long id) {
        coachPostService.deleteFreePost(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }
}