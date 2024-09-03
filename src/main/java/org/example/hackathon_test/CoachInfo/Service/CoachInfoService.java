package org.example.hackathon_test.CoachInfo.Service;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.CoachInfo.Dto.CoachInfoDto;
import org.example.hackathon_test.CoachInfo.Entity.CoachInfo;
import org.example.hackathon_test.CoachInfo.Repository.CoachInfoRepository;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.example.hackathon_test.User.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class CoachInfoService {
    private final CoachInfoRepository coachInfoRepository;
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Transactional
    public void createCoachInfo(String username, CoachInfoDto coachInfoDto) {
        User user = loginRepository.findByUsername(username);
        if (user == null) return;
        CoachInfo coachInfo = new CoachInfo();

        coachInfo.setUser(user);
        coachInfo.setName(user.getName());
        coachInfo.setAge(coachInfoDto.getAge());
        coachInfo.setCareer(coachInfoDto.getCareer());
        coachInfo.setSex(coachInfoDto.getSex());
        coachInfo.setNormalLicense(coachInfoDto.getNormalLicense());
        coachInfo.setSportsLicense(coachInfoDto.getSportsLicense());
        coachInfo.setCprLicense(coachInfoDto.getCprLicense());
        coachInfo.setEtcLicense(coachInfoDto.getEtcLicense());

        user.setIsFirst(false);
        user.setIsCoach(true);
        user.setRole("ROLE_COACH");

        userRepository.save(user);
        coachInfoRepository.save(coachInfo);
    }

    // 모든 유저 정보 가져오기
    public List<CoachInfo> getAllCoachInfos() {
        return coachInfoRepository.findAll();
    }

    public Optional<CoachInfo> getCoachInfoByUsername(String username) {
        User user = loginRepository.findByUsername(username);
        return coachInfoRepository.findByUser(user);
    }

    // 수정
    @Transactional
    public CoachInfo updateCoachInfo(String username, CoachInfoDto coachInfoDto) {
        User user = loginRepository.findByUsername(username);
        Optional<CoachInfo> userInfoOptional = coachInfoRepository.findByUser(user);
        if (userInfoOptional.isPresent()) {
            CoachInfo coachInfo = userInfoOptional.get();

            if (coachInfoDto.getAge() != null) coachInfo.setAge(coachInfoDto.getAge());
            if (coachInfoDto.getCareer() != null) coachInfo.setCareer(coachInfoDto.getCareer());
            if (coachInfoDto.getSex() != null) coachInfo.setSex(coachInfoDto.getSex());
            if (coachInfoDto.getNormalLicense() != null) coachInfo.setNormalLicense(coachInfoDto.getNormalLicense());
            if (coachInfoDto.getSportsLicense() != null) coachInfo.setSportsLicense(coachInfoDto.getSportsLicense());
            if (coachInfoDto.getCprLicense() != null) coachInfo.setCprLicense(coachInfoDto.getCprLicense());
            if (coachInfoDto.getEtcLicense() != null) coachInfo.setEtcLicense(coachInfoDto.getEtcLicense());

            return coachInfoRepository.save(coachInfo);
        }
        else throw new RuntimeException("CoachInfo not found with username " + username);
    }

    // Delete
    public void deleteCoachInfo(String username) {
        User user = loginRepository.findByUsername(username);
        userRepository.delete(user);
    }
}