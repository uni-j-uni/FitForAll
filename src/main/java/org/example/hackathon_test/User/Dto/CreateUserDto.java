package org.example.hackathon_test.User.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.User.Entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
public class CreateUserDto {
    private String name;
    private String email;
    private String username;
    private String password;

    public static CreateUserDto from(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        CreateUserDto createUserDto = new CreateUserDto();

        createUserDto.setName(user.getName());
        createUserDto.setEmail(user.getEmail());
        createUserDto.setUsername(user.getUsername());
        createUserDto.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return createUserDto;
    }
}