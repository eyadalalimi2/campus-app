package com.eyadalalimi.medcurriculum.data.dto.auth;

import com.eyadalalimi.medcurriculum.domain.model.User;

// يستوعب كلا الحالتين: {token, user} أو user فقط.
public class AuthResponse {
    public String token;
    public User user;
}
