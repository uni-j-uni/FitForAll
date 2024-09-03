package org.example.hackathon_test.RPComment.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.RPComment.Dto.GetRPCommentDto;
import org.example.hackathon_test.RPComment.Dto.RPCommentDto;
import org.example.hackathon_test.RPComment.Entity.RPComment;
import org.example.hackathon_test.RPComment.Service.RPCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/rpcomment")
public class RPCommentController {
    private final RPCommentService rpCommentService;

    @PostMapping("/{recruitPostId}")
    public ResponseEntity<Void> createRPComment(Authentication authentication, @PathVariable(name="recruitPostId") Long recruitPostId, @RequestBody RPCommentDto rpCommentDto) {
        rpCommentService.createRPComment(authentication.getName(), recruitPostId, rpCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRPCommentDto> getRPCommentById(@PathVariable(name="id") Long id) {
        Optional<RPComment> rpCommentOptional = rpCommentService.getRPCommentById(id);
        if (rpCommentOptional.isPresent()) {
            GetRPCommentDto dto = GetRPCommentDto.from(rpCommentOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/recruitPost/{recruitPostId}")
    public ResponseEntity<List<GetRPCommentDto>> getRPCommentsByRecruitPostId(@PathVariable(name="recruitPostId") Long recruitPostId) {
        List<RPComment> rpComments = rpCommentService.getRPCommentByRecruitPostId(recruitPostId);
        List<GetRPCommentDto> getRPCommentDtos = new ArrayList<>();

        for (RPComment rpComment : rpComments) {
            GetRPCommentDto dto = GetRPCommentDto.from(rpComment);
            getRPCommentDtos.add(dto);
        }
        return ResponseEntity.ok(getRPCommentDtos);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RPCommentDto> updateRPComment(@PathVariable(name="id") Long id, @RequestBody RPCommentDto rpCommentDto) {
        try {
            RPComment updatedRPComment = rpCommentService.updateRPComment(id, rpCommentDto);
            return ResponseEntity.ok(RPCommentDto.from(updatedRPComment));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRPComment(Authentication authentication, @PathVariable(name="id") Long id) {
        rpCommentService.deleteRPComment(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }
}