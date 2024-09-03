package org.example.hackathon_test.Jwt.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hackathon_test.Jwt.Dto.CustomOAuth2User;
import org.example.hackathon_test.Jwt.Dto.GoogleResponse;
import org.example.hackathon_test.Jwt.Dto.NaverResponse;
import org.example.hackathon_test.Jwt.Dto.OAuth2Dto;
import org.example.hackathon_test.Jwt.Dto.OAuth2Response;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final LoginRepository loginRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;

        if(registrationId.equals("naver")){ oAuth2Response = new NaverResponse(oAuth2User.getAttributes()); }
        else if(registrationId.equals("google")){ oAuth2Response=new GoogleResponse(oAuth2User.getAttributes()); }
        else return null;

        String username = oAuth2Response.getUsername();

        User existData = loginRepository.findByUsername(username);

        if(existData == null){
            User user = new User();
            user.setUsername(oAuth2Response.getUsername());
            user.setEmail(oAuth2Response.getEmail());
            user.setName(oAuth2Response.getName());
            user.setPassword(oAuth2Response.getPassword());
            user.setRole("ROLE_SOCIAL");

            loginRepository.save(user);

            OAuth2Dto oAuth2DTO = new OAuth2Dto();
            oAuth2DTO.setUsername(oAuth2Response.getUsername());
            oAuth2DTO.setEmail(oAuth2Response.getEmail());
            oAuth2DTO.setName(oAuth2Response.getName());
            oAuth2DTO.setPassword(oAuth2Response.getPassword());
            oAuth2DTO.setRole("ROLE_SOCIAL");

            return new CustomOAuth2User(oAuth2DTO);
        }
        else {
            existData.setUsername(oAuth2Response.getUsername());
            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());
            existData.setPassword(oAuth2Response.getPassword());
            loginRepository.save(existData);

            OAuth2Dto oAuth2DTO = new OAuth2Dto();
            oAuth2DTO.setUsername(existData.getUsername());
            oAuth2DTO.setEmail(existData.getEmail());
            oAuth2DTO.setName(existData.getName());
            oAuth2DTO.setRole(existData.getRole());
            oAuth2DTO.setPassword(existData.getPassword());
            return new CustomOAuth2User(oAuth2DTO);
        }
    }

}
