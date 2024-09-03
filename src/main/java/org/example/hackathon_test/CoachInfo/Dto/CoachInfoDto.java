package org.example.hackathon_test.CoachInfo.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.CoachInfo.Entity.CoachInfo;

@Getter
@Setter
public class CoachInfoDto {
    private Integer age;
    private String career;
    private String sex;
    private String normalLicense;
    private String sportsLicense;
    private String cprLicense;
    private String etcLicense;

    public static CoachInfoDto from(CoachInfo coachInfo) {
        CoachInfoDto coachInfoDto = new CoachInfoDto();

        coachInfoDto.setAge(coachInfo.getAge());
        coachInfoDto.setCareer(coachInfo.getCareer());
        coachInfoDto.setSex(coachInfo.getSex());
        coachInfoDto.setNormalLicense(coachInfo.getNormalLicense());
        coachInfoDto.setSportsLicense(coachInfo.getSportsLicense());
        coachInfoDto.setCprLicense(coachInfo.getCprLicense());
        coachInfoDto.setEtcLicense(coachInfo.getEtcLicense());

        return coachInfoDto;
    }
}
