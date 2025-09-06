package com.eyadalalimi.medcurriculum.data.dto.auth;

public class LoginRequest {
    public String email;
    public String password;
    public String device_name;

    public LoginRequest(String email, String password, String deviceName) {
        this.email = email;
        this.password = password;
        this.device_name = deviceName;
    }
}
