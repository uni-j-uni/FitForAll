package org.example.hackathon_test.CoachInfo.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.CoachInfo.Entity.CoachInfo;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetCoachInfoDto {
    private String username;
    private String name;
    private Integer age;
    private String career;
    private String sex;
    private String normalLicense;
    private String sportsLicense;
    private String cprLicense;
    private String etcLicense;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static GetCoachInfoDto from(CoachInfo coachInfo) {
        GetCoachInfoDto getCoachInfoDto = new GetCoachInfoDto();

        getCoachInfoDto.setUsername(coachInfo.getUser().getUsername());
        getCoachInfoDto.setName(coachInfo.getUser().getName());
        getCoachInfoDto.setAge(coachInfo.getAge());
        getCoachInfoDto.setCareer(coachInfo.getCareer());
        getCoachInfoDto.setSex(coachInfo.getSex());
        getCoachInfoDto.setNormalLicense(coachInfo.getNormalLicense());
        getCoachInfoDto.setSportsLicense(coachInfo.getSportsLicense());
        getCoachInfoDto.setCprLicense(coachInfo.getCprLicense());
        getCoachInfoDto.setEtcLicense(coachInfo.getEtcLicense());
        getCoachInfoDto.setCreateDate(coachInfo.getCreateDate());
        getCoachInfoDto.setModifyDate(coachInfo.getModifyDate());

        return getCoachInfoDto;
    }
}
