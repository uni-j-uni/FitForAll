package org.example.hackathon_test.CoachInfo.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.CoachInfo.Dto.GetCoachInfoDto;
import org.example.hackathon_test.CoachInfo.Dto.CoachInfoDto;
import org.example.hackathon_test.CoachInfo.Entity.CoachInfo;
import org.example.hackathon_test.CoachInfo.Service.CoachInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/coachinfo")
public class CoachInfoController {
    private final CoachInfoService coachInfoService;

    // 특정 유저 정보 입력
    @PostMapping
    public ResponseEntity<Void> createCoachInfo(Authentication authentication, @RequestBody CoachInfoDto coachInfoDto) {
        coachInfoService.createCoachInfo(authentication.getName(), coachInfoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<GetCoachInfoDto>> getAllCoachInfos() {
        List<CoachInfo> coachInfos = coachInfoService.getAllCoachInfos();
        List<GetCoachInfoDto> getCoachInfoDtos = new ArrayList<>();
        for (CoachInfo coachInfo : coachInfos) {
            GetCoachInfoDto dto = GetCoachInfoDto.from(coachInfo);
            getCoachInfoDtos.add(dto);
        }
        return ResponseEntity.ok(getCoachInfoDtos);
    }

    // username으로 개인 사용자 정보 조회
    @GetMapping("/username")
    public ResponseEntity<GetCoachInfoDto> getCoachInfoByUser(Authentication authentication) {
        Optional<CoachInfo> coachInfoOptional = coachInfoService.getCoachInfoByUsername(authentication.getName());
        if (coachInfoOptional.isPresent()) {
            GetCoachInfoDto dto = GetCoachInfoDto.from(coachInfoOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    // 유저 정보 변경
    @PatchMapping()
    public ResponseEntity<CoachInfoDto> updateCoachInfo(Authentication authentication, @RequestBody CoachInfoDto coachInfoDto) {
        try {
            CoachInfo updatedCoachInfo = coachInfoService.updateCoachInfo(authentication.getName(), coachInfoDto);
            return ResponseEntity.ok(CoachInfoDto.from(updatedCoachInfo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 유저 정보 삭제
    @DeleteMapping()
    public ResponseEntity<Void> deleteCoachInfo(Authentication authentication) {
        coachInfoService.deleteCoachInfo(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}