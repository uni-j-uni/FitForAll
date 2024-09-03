package org.example.hackathon_test.User.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.User.Entity.User;

@Getter
@Setter
public class GetUserDto {
    private String username;
    private String name;
    private String email;
    private Boolean isFirstLogin;

    public static GetUserDto from(User user) {
        GetUserDto getUserDto = new GetUserDto() ;

        getUserDto.setUsername(user.getUsername());
        getUserDto.setName(user.getName());
        getUserDto.setEmail(user.getEmail());
        getUserDto.setIsFirstLogin(user.getIsFirst());

        return getUserDto;
    }
}