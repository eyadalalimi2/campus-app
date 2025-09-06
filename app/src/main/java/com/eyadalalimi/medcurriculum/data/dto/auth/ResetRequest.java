package com.eyadalalimi.medcurriculum.data.dto.auth;

public class ResetRequest {
    public String token;
    public String email;
    public String password;
    public String password_confirmation;

    public ResetRequest(String token, String email, String newPassword) {
        this.token = token;
        this.email = email;
        this.password = newPassword;
        this.password_confirmation = newPassword;
    }
}
