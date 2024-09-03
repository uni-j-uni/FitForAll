package org.example.hackathon_test.FreePost.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.FreePost.Dto.FreePostDto;
import org.example.hackathon_test.FreePost.Dto.GetFreePostDto;
import org.example.hackathon_test.FreePost.Entity.FreePost;
import org.example.hackathon_test.FreePost.Service.FreePostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/free")
public class FreePostController {
    private final FreePostService freePostService;


    // 자유게시판 게시물 생성
    @PostMapping
    public ResponseEntity<Void> createFreePost(Authentication authentication, @RequestBody FreePostDto freePostDto) {
        freePostService.createFreePost(authentication.getName(), freePostDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 모든 자유 게시물 조회
    @GetMapping
    public ResponseEntity<List<GetFreePostDto>> getAllFreePosts() {
        List<FreePost> freePosts = freePostService.getAllFreePosts();
        List<GetFreePostDto> getFreePostDtos = new ArrayList<>();
        for (FreePost freePost : freePosts) {
            GetFreePostDto dto = GetFreePostDto.from(freePost);
            getFreePostDtos.add(dto);
        }
        return ResponseEntity.ok(getFreePostDtos);
    }

    // 자유 게시물 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetFreePostDto> getFreePostById(@PathVariable(name="id") Long id) {
        Optional<FreePost> freePostOptional = freePostService.getFreePostById(id);
        if (freePostOptional.isPresent()) {
            GetFreePostDto dto = GetFreePostDto.from(freePostOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    // Update - Patch
    @PatchMapping("/{id}")
    public ResponseEntity<FreePostDto> updateFreePost(Authentication authentication, @PathVariable(name="id") Long id, @RequestBody FreePostDto freePostDto) {
        try {
            FreePost updatedFreePost = freePostService.updateFreePost(authentication.getName(), id, freePostDto);
            return ResponseEntity.ok(FreePostDto.from(updatedFreePost));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFreePost(Authentication authentication, @PathVariable(name="id") Long id) {
        freePostService.deleteFreePost(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }
}