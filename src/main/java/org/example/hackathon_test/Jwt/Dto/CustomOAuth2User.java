package org.example.hackathon_test.Jwt.Dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private final OAuth2Dto oAuth2Dto;

    public CustomOAuth2User(OAuth2Dto oAuth2Dto){
        this.oAuth2Dto = oAuth2Dto;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return oAuth2Dto.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return oAuth2Dto.getName();
    }
    public String getUsername() { return oAuth2Dto.getUsername(); }
}
