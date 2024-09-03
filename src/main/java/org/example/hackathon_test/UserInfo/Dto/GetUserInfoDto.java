package org.example.hackathon_test.UserInfo.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.UserInfo.Entity.UserInfo;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetUserInfoDto {
    private String username;
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
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static GetUserInfoDto from(UserInfo userInfo) {
        GetUserInfoDto getUserInfoDto = new GetUserInfoDto();

        getUserInfoDto.setUsername(userInfo.getUser().getUsername());
        getUserInfoDto.setIsGuardian(userInfo.getIsGuardian());
        getUserInfoDto.setNickname(userInfo.getNickname());
        getUserInfoDto.setAge(userInfo.getAge());
        getUserInfoDto.setSex(userInfo.getSex());
        getUserInfoDto.setDisabilityCF(userInfo.getDisabilityCF());
        getUserInfoDto.setDisabilityK(userInfo.getDisabilityK());
        getUserInfoDto.setDisabilityKK(userInfo.getDisabilityKK());
        getUserInfoDto.setMuscle(userInfo.getMuscle());
        getUserInfoDto.setStretching(userInfo.getStretching());
        getUserInfoDto.setBall(userInfo.getBall());
        getUserInfoDto.setWater(userInfo.getWater());
        getUserInfoDto.setCardio(userInfo.getCardio());
        getUserInfoDto.setExerciseIntensity(userInfo.getExerciseIntensity());
        getUserInfoDto.setCreateDate(userInfo.getCreateDate());
        getUserInfoDto.setModifyDate(userInfo.getModifyDate());

        return getUserInfoDto;
    }
}