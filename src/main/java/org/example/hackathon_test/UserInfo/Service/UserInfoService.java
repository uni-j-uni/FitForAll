package org.example.hackathon_test.UserInfo.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.example.hackathon_test.User.Repository.UserRepository;
import org.example.hackathon_test.UserInfo.Dto.UserInfoDto;
import org.example.hackathon_test.UserInfo.Entity.UserInfo;
import org.example.hackathon_test.UserInfo.Repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Transactional
    public void createUserInfo(String username, UserInfoDto userInfoDto) {
        User user = loginRepository.findByUsername(username);
        if (user == null) return;
        UserInfo userInfo = new UserInfo();

        userInfo.setUser(user);
        userInfo.setIsGuardian(userInfoDto.getIsGuardian());
        userInfo.setNickname(userInfoDto.getNickname());
        userInfo.setAge(userInfoDto.getAge());
        userInfo.setSex(userInfoDto.getSex());
        userInfo.setDisabilityCF(userInfoDto.getDisabilityCF());
        userInfo.setDisabilityK(userInfoDto.getDisabilityK());
        userInfo.setDisabilityKK(userInfoDto.getDisabilityKK());
        userInfo.setMuscle(userInfoDto.getMuscle());
        userInfo.setStretching(userInfoDto.getStretching());
        userInfo.setBall(userInfoDto.getBall());
        userInfo.setWater(userInfoDto.getWater());
        userInfo.setCardio(userInfoDto.getCardio());
        userInfo.setExerciseIntensity(userInfoDto.getExerciseIntensity());

        user.setIsFirst(false);
        user.setIsCoach(false);
        user.setRole("ROLE_USER");

        userRepository.save(user);
        userInfoRepository.save(userInfo);
    }

    // 모든 유저 정보 가져오기
    public List<UserInfo> getAllUserInfos() {
        return userInfoRepository.findAll();
    }

    public Optional<UserInfo> getUserInfoByUsername(String username) {
        User user = loginRepository.findByUsername(username);
        return userInfoRepository.findByUser(user);
    }

    public Boolean isNicknameExist(String nickname) {
        List<UserInfo> userInfos = userInfoRepository.findByNickname(nickname);
        return userInfos.isEmpty();
    }

    // 수정
    @Transactional
    public UserInfo updateUserInfo(String username, UserInfoDto userInfoDto) {
        User user = loginRepository.findByUsername(username);
        Optional<UserInfo> userInfoOptional = userInfoRepository.findByUser(user);
        if (userInfoOptional.isPresent()) {
            UserInfo userInfo = userInfoOptional.get();

            if (userInfoDto.getIsGuardian() != null) userInfo.setIsGuardian(userInfoDto.getIsGuardian());
            if (userInfoDto.getNickname() != null) userInfo.setNickname(userInfoDto.getNickname());
            if (userInfoDto.getAge() != null) userInfo.setAge(userInfoDto.getAge());
            if (userInfoDto.getSex() != null) userInfo.setSex(userInfoDto.getSex());
            if (userInfoDto.getDisabilityCF() != null) userInfo.setDisabilityCF(userInfoDto.getDisabilityCF());
            if (userInfoDto.getDisabilityK() != null) userInfo.setDisabilityK(userInfoDto.getDisabilityK());
            if (userInfoDto.getDisabilityKK() != null) userInfo.setDisabilityKK(userInfoDto.getDisabilityKK());
            if (userInfoDto.getMuscle() != null) userInfo.setMuscle(userInfoDto.getMuscle());
            if (userInfoDto.getStretching() != null) userInfo.setMuscle(userInfoDto.getStretching());
            if (userInfoDto.getBall() != null) userInfo.setMuscle(userInfoDto.getBall());
            if (userInfoDto.getWater() != null) userInfo.setMuscle(userInfoDto.getWater());
            if (userInfoDto.getCardio() != null) userInfo.setMuscle(userInfoDto.getCardio());
            if (userInfoDto.getExerciseIntensity() != null) userInfo.setExerciseIntensity(userInfoDto.getExerciseIntensity());

            return userInfoRepository.save(userInfo);
        }
        else throw new RuntimeException("UserInfo not found with username " + username);
    }

    // Delete
    public void deleteUserInfo(String username) {
        User user = loginRepository.findByUsername(username);
        userRepository.delete(user);
    }
}