package com.eyadalalimi.medcurriculum.core.network;

import androidx.annotation.NonNull;

import com.eyadalalimi.medcurriculum.core.storage.TokenManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final TokenManager tokenManager;

    public AuthInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder b = original.newBuilder()
                .addHeader("Accept", "application/json");

        String token = tokenManager.getToken();
        if (token != null && !token.isEmpty()) {
            b.addHeader("Authorization", "Bearer " + token);
        }
        return chain.proceed(b.build());
    }
}
