package org.example.hackathon_test.UserInfo.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.UserInfo.Dto.GetUserInfoDto;
import org.example.hackathon_test.UserInfo.Dto.UserInfoDto;
import org.example.hackathon_test.UserInfo.Entity.UserInfo;
import org.example.hackathon_test.UserInfo.Service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    private final UserInfoService userInfoService;

    // 특정 유저 정보 입력
    @PostMapping
    public ResponseEntity<Void> createUserInfo(Authentication authentication, @RequestBody UserInfoDto userInfoDto) {
        userInfoService.createUserInfo(authentication.getName(), userInfoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<GetUserInfoDto>> getAllUserInfos() {
        List<UserInfo> userInfos = userInfoService.getAllUserInfos();
        List<GetUserInfoDto> getUserInfoDtos = new ArrayList<>();
        for (UserInfo userInfo : userInfos) {
            GetUserInfoDto dto = GetUserInfoDto.from(userInfo);
            getUserInfoDtos.add(dto);
        }
        return ResponseEntity.ok(getUserInfoDtos);
    }

    // username으로 개인 사용자 정보 조회
    @GetMapping("/username")
    public ResponseEntity<GetUserInfoDto> getUserInfoByUser(Authentication authentication) {
        Optional<UserInfo> userInfoOptional = userInfoService.getUserInfoByUsername(authentication.getName());
        if (userInfoOptional.isPresent()) {
            GetUserInfoDto dto = GetUserInfoDto.from(userInfoOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("nickname/{nickname}")
    public Boolean isNicknameExist(@PathVariable(name = "nickname") String nickname) {
        return userInfoService.isNicknameExist(nickname);
    }

    // 유저 정보 변경
    @PatchMapping()
    public ResponseEntity<UserInfoDto> updateUserInfo(Authentication authentication, @RequestBody UserInfoDto userInfoDto) {
        try {
            UserInfo updatedUserInfo = userInfoService.updateUserInfo(authentication.getName(), userInfoDto);
            return ResponseEntity.ok(UserInfoDto.from(updatedUserInfo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 유저 정보 삭제
    @DeleteMapping()
    public ResponseEntity<Void> deleteUserInfo(Authentication authentication) {
        userInfoService.deleteUserInfo(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}