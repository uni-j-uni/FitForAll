package org.example.hackathon_test.FPComment.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.FPComment.Dto.FPCommentDto;
import org.example.hackathon_test.FPComment.Dto.GetFPCommentDto;
import org.example.hackathon_test.FPComment.Entity.FPComment;
import org.example.hackathon_test.FPComment.Service.FPCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/fpcomment")
public class FPCommentController {
    private final FPCommentService fpCommentService;

    // 장소모집 게시물 생성
    @PostMapping("/{freePostId}")
    public ResponseEntity<Void> createFPComment(Authentication authentication, @PathVariable(name="freePostId") Long freePostId, @RequestBody FPCommentDto FPCommentDto) {
        fpCommentService.createFPComment(authentication.getName(), freePostId, FPCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 모든 장소모집 게시물 조회
    @GetMapping
    public ResponseEntity<List<GetFPCommentDto>> getAllFPComments() {
        List<FPComment> fpComments = fpCommentService.getAllFPComments();
        List<GetFPCommentDto> getFPCommentDtos = new ArrayList<>();
        for (FPComment fpComment : fpComments) {
            GetFPCommentDto dto = GetFPCommentDto.from(fpComment);
            getFPCommentDtos.add(dto);
        }
        return ResponseEntity.ok(getFPCommentDtos);
    }

    // 장소모집 게시물 댓글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetFPCommentDto> getFPCommentById(@PathVariable(name="id") Long id) {
        Optional<FPComment> fpCommentOptional = fpCommentService.getFPCommentById(id);
        if (fpCommentOptional.isPresent()) {
            GetFPCommentDto dto = GetFPCommentDto.from(fpCommentOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    // 장소모집 게시물 아이디로 조회
    @GetMapping("/freePost/{freePostId}")
    public ResponseEntity<List<GetFPCommentDto>> getFPCommentsByFreePostId(@PathVariable(name="freePostId") Long freePostId) {
        List<FPComment> fpComments = fpCommentService.getFPCommentByFreePostId(freePostId);
        List<GetFPCommentDto> getFPCommentDtos = new ArrayList<>();

        for (FPComment fpComment : fpComments) {
            GetFPCommentDto dto = GetFPCommentDto.from(fpComment);
            getFPCommentDtos.add(dto);
        }
        return ResponseEntity.ok(getFPCommentDtos);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FPCommentDto> updateFPComment(@PathVariable(name="id") Long id, @RequestBody FPCommentDto fpCommentDto) {
        try {
            FPComment updatedFPComment = fpCommentService.updateFPComment(id, fpCommentDto);
            return ResponseEntity.ok(FPCommentDto.from(updatedFPComment));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFPComment(Authentication authentication, @PathVariable(name="id") Long id) {
        fpCommentService.deleteFPComment(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }
}