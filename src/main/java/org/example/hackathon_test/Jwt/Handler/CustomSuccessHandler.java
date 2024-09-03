package org.example.hackathon_test.Jwt.Handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.hackathon_test.Jwt.JWTUtil;
import org.example.hackathon_test.Jwt.Dto.CustomOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Slf4j
@AllArgsConstructor
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // Generate JWT token
        String token = jwtUtil.createJwt(username, role, 60 * 60 * 10000L);

        // Add token to response header
        //response.addHeader("Authorization", "Bearer " + token);
        // Optionally, you can redirect or write a custom response body
        //response.sendRedirect("http://localhost:5173?code="+"Bearer "+token);
        // 하나만 써여돼
        response.sendRedirect("https://final-2--astonishing-tulumba-94a4e3.netlify.app?code="+"Bearer "+token);

    }
}
