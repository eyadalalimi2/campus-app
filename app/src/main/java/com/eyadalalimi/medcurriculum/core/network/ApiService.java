package com.eyadalalimi.medcurriculum.core.network;

import com.eyadalalimi.medcurriculum.data.dto.auth.ForgotRequest;
import com.eyadalalimi.medcurriculum.data.dto.auth.LoginRequest;
import com.eyadalalimi.medcurriculum.data.dto.auth.RegisterRequest;
import com.eyadalalimi.medcurriculum.data.dto.auth.ResetRequest;
import com.eyadalalimi.medcurriculum.data.dto.profile.UpdatePasswordRequest;
import com.eyadalalimi.medcurriculum.data.dto.profile.UpdateProfileRequest;
import com.eyadalalimi.medcurriculum.data.dto.common.ApiMessageResponse;
import com.eyadalalimi.medcurriculum.domain.model.User;
import com.eyadalalimi.medcurriculum.data.dto.auth.AuthResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    // Auth
    @POST("auth/register")
    Call<AuthResponse> register(@Body RegisterRequest body);

    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest body);

    @POST("auth/logout")
    Call<ApiMessageResponse> logout();

    @POST("auth/forgot-password")
    Call<ApiMessageResponse> forgot(@Body ForgotRequest body);

    @POST("auth/reset-password")
    Call<ApiMessageResponse> reset(@Body ResetRequest body);

    @POST("auth/email/verification-notification")
    Call<ApiMessageResponse> resendVerification();

    @GET("auth/verify-email/{id}/{hash}")
    Call<ApiMessageResponse> verifyEmail(@Path("id") long id, @Path("hash") String hash, @Query("expires") String expires, @Query("signature") String signature);

    // Profile
    @GET("profile")
    Call<User> getProfile();

    @PATCH("profile")
    Call<User> updateProfile(@Body UpdateProfileRequest body);

    @PATCH("profile/password")
    Call<ApiMessageResponse> updatePassword(@Body UpdatePasswordRequest body);

    @Multipart
    @POST("profile/avatar")
    Call<User> upsertAvatar(@Part MultipartBody.Part avatar);

    @DELETE("profile/avatar")
    Call<ApiMessageResponse> deleteAvatar();

    @GET("user")
    Call<User> me();
}
