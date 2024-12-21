package com.example.OnlineJobPortal.dto;

public class LoginResponseDto {
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String accessToken;
}
