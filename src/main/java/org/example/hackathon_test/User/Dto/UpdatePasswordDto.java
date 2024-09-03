package org.example.hackathon_test.User.Dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hackathon_test.User.Entity.User;

@Getter
@Setter
public class UpdatePasswordDto {
    private String currentPassword;
    private String newPassword;

    public static UpdatePasswordDto from(User user) {
        UpdatePasswordDto updatePasswordDto = new UpdatePasswordDto();

        updatePasswordDto.setNewPassword(user.getPassword());

        return updatePasswordDto;
    }
}