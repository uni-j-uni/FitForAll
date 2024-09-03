package org.example.hackathon_test.CPComment.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.CPComment.Dto.CPCommentDto;
import org.example.hackathon_test.CPComment.Dto.GetCPCommentDto;
import org.example.hackathon_test.CPComment.Entity.CPComment;
import org.example.hackathon_test.CPComment.Service.CPCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/cpcomment")
public class CPCommentController {
    private final CPCommentService cpCommentService;

    // 장소모집 게시물 생성
    @PostMapping("/{coachPostId}")
    public ResponseEntity<Void> createCPComment(Authentication authentication, @PathVariable(name="coachPostId") Long coachPostId, @RequestBody CPCommentDto cpCommentDto) {
        cpCommentService.createCPComment(authentication.getName(), coachPostId, cpCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 모든 장소모집 게시물 조회
    @GetMapping
    public ResponseEntity<List<GetCPCommentDto>> getAllCPComments() {
        List<CPComment> cpComments = cpCommentService.getAllCPComments();
        List<GetCPCommentDto> getCPCommentDtos = new ArrayList<>();
        for (CPComment cpComment : cpComments) {
            GetCPCommentDto dto = GetCPCommentDto.from(cpComment);
            getCPCommentDtos.add(dto);
        }
        return ResponseEntity.ok(getCPCommentDtos);
    }

    // 장소모집 게시물 댓글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetCPCommentDto> getCPCommentById(@PathVariable(name="id") Long id) {
        Optional<CPComment> cpCommentOptional = cpCommentService.getCPCommentById(id);
        if (cpCommentOptional.isPresent()) {
            GetCPCommentDto dto = GetCPCommentDto.from(cpCommentOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    // 장소모집 게시물 아이디로 조회
    @GetMapping("/coachPost/{coachPostId}")
    public ResponseEntity<List<GetCPCommentDto>> getCPCommentsByCoachPostId(@PathVariable(name="coachPostId") Long coachPostId) {
        List<CPComment> cpComments = cpCommentService.getCPCommentByCoachPostId(coachPostId);
        List<GetCPCommentDto> getCPCommentDtos = new ArrayList<>();

        for (CPComment cpComment : cpComments) {
            GetCPCommentDto dto = GetCPCommentDto.from(cpComment);
            getCPCommentDtos.add(dto);
        }
        return ResponseEntity.ok(getCPCommentDtos);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CPCommentDto> updateCPComment(@PathVariable(name="id") Long id, @RequestBody CPCommentDto cpCommentDto) {
        try {
            CPComment updatedCPComment = cpCommentService.updateCPComment(id, cpCommentDto);
            return ResponseEntity.ok(CPCommentDto.from(updatedCPComment));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCPComment(Authentication authentication, @PathVariable(name="id") Long id) {
        cpCommentService.deleteCPComment(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }
}