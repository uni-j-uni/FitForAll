package org.example.hackathon_test.User.Controller;

import lombok.AllArgsConstructor;
import org.example.hackathon_test.User.Dto.FindUserDto;
import org.example.hackathon_test.User.Dto.GetUserDto;
import org.example.hackathon_test.User.Dto.UpdatePasswordDto;
import org.example.hackathon_test.User.Dto.CreateUserDto;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@ResponseBody
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    // Create - Post
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/isFirstLogin")
    public Boolean isFirstLogin(Authentication authentication) { return userService.isFirstLogin(authentication.getName()); }

    @PostMapping("/isCoach")
    public Boolean isCoach(Authentication authentication) { return userService.isCoach(authentication.getName()); }

    @PostMapping("/findUsername")
    public String getUserByNameAndEmail(@RequestBody FindUserDto findUserDto) {
        return userService.findUsername(findUserDto.getName(), findUserDto.getEmail());
    }

    // Read - GET
    // 모든 사용자 조회
    @GetMapping
    public ResponseEntity<List<GetUserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<GetUserDto> getUserDtos = new ArrayList<>();

        for (User user : users) {
            GetUserDto dto = GetUserDto.from(user);
            getUserDtos.add(dto);
        }
        return ResponseEntity.ok(getUserDtos);
    }

    @GetMapping("/username")
    public ResponseEntity<GetUserDto> getUserByUsername(Authentication authentication) {
        Optional<User> userOptional = userService.getUserByUsername(authentication.getName());
        if (userOptional.isPresent()) {
            GetUserDto dto = GetUserDto.from(userOptional.get());
            return ResponseEntity.ok(dto);
        }
        else return ResponseEntity.notFound().build();
    }

    // username으로 조회
    @GetMapping("/{username}")
    public boolean isUsernameExist(@PathVariable (name = "username") String username) {
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isPresent()) return false;
        else return true;
    }

    // Update - PATCH
    @PatchMapping()
    public ResponseEntity<CreateUserDto> updateUser(Authentication authentication, @RequestBody UpdatePasswordDto updatePasswordDto) {
        try {
            User updatedUser = userService.updateUser(authentication.getName(), updatePasswordDto);
            return ResponseEntity.ok(CreateUserDto.from(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete
    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}