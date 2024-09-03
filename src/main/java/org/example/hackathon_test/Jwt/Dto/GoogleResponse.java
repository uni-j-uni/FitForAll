package org.example.hackathon_test.Jwt.Dto;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class GoogleResponse implements OAuth2Response{
    private final Map<String, Object> attribute;

    public GoogleResponse(Map<String, Object> attribute) { this.attribute = attribute; }
    @Override
    public String getProviderId() { return attribute.get("sub").toString(); }
    @Override
    public String getUsername() { return attribute.get("email").toString(); }
    @Override
    public String getName() { return attribute.get("name").toString(); }

    @Override
    public String getEmail(){return attribute.get("email").toString(); }

    @Override
    public String getPassword(){return RandomStringUtils.randomAlphabetic(50);}
}
