package org.example.hackathon_test.User.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.User.Entity.User;

@Getter
@Setter
public class FindUserDto {
    private String name;
    private String email;

    public static FindUserDto from(User user) {
        FindUserDto findUserDto = new FindUserDto() ;

        findUserDto.setName(user.getName());
        findUserDto.setEmail(user.getEmail());

        return findUserDto;
    }
}