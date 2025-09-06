package com.eyadalalimi.medcurriculum.data.dto.profile;

public class UpdatePasswordRequest {
    public String current_password;
    public String password;
    public String password_confirmation;

    public UpdatePasswordRequest(String current, String next) {
        this.current_password = current;
        this.password = next;
        this.password_confirmation = next;
    }
}
