package org.example.hackathon_test.Jwt.LoginService;


import lombok.AllArgsConstructor;
import org.example.hackathon_test.Jwt.Dto.CustomUserDetails;
import org.example.hackathon_test.User.Entity.User;
import org.example.hackathon_test.User.Repository.LoginRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginRepository loginRepository;

    //데이터베이스 특정 유저 조회
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //조회
        User userData = loginRepository.findByUsername(username);

        //검증
        if(userData!=null){
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
