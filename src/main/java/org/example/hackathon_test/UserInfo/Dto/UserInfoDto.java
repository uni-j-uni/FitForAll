package org.example.hackathon_test.UserInfo.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.UserInfo.Entity.UserInfo;

@Getter
@Setter
public class UserInfoDto {
    private Boolean isGuardian;
    private String nickname;
    private Integer age;
    private String sex;
    private String disabilityCF;
    private String disabilityK;
    private String disabilityKK;
    private Boolean muscle;
    private Boolean stretching;
    private Boolean ball;
    private Boolean water;
    private Boolean cardio;
    private String exerciseIntensity;

    public static UserInfoDto from(UserInfo userInfo) {
        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setIsGuardian(userInfo.getIsGuardian());
        userInfoDto.setNickname(userInfo.getNickname());
        userInfoDto.setAge(userInfo.getAge());
        userInfoDto.setSex(userInfo.getSex());
        userInfoDto.setDisabilityCF(userInfo.getDisabilityCF());
        userInfoDto.setDisabilityK(userInfo.getDisabilityK());
        userInfoDto.setDisabilityKK(userInfo.getDisabilityKK());
        userInfoDto.setMuscle(userInfo.getMuscle());
        userInfoDto.setStretching(userInfo.getStretching());
        userInfoDto.setBall(userInfo.getBall());
        userInfoDto.setWater(userInfo.getWater());
        userInfoDto.setCardio(userInfo.getCardio());
        userInfoDto.setExerciseIntensity(userInfo.getExerciseIntensity());

        return userInfoDto;
    }
}
