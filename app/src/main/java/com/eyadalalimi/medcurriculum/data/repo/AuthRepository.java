package com.eyadalalimi.medcurriculum.data.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eyadalalimi.medcurriculum.core.network.ApiClient;
import com.eyadalalimi.medcurriculum.core.network.ApiService;
import com.eyadalalimi.medcurriculum.core.network.ErrorParser;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.storage.TokenManager;
import com.eyadalalimi.medcurriculum.data.dto.auth.ForgotRequest;
import com.eyadalalimi.medcurriculum.data.dto.auth.LoginRequest;
import com.eyadalalimi.medcurriculum.data.dto.auth.RegisterRequest;
import com.eyadalalimi.medcurriculum.data.dto.auth.ResetRequest;
import com.eyadalalimi.medcurriculum.data.dto.common.ApiMessageResponse;
import com.eyadalalimi.medcurriculum.data.dto.auth.AuthResponse;
import com.eyadalalimi.medcurriculum.domain.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private final ApiService api;
    private final TokenManager tokens;

    public AuthRepository(Context ctx) {
        this.api = ApiClient.create(ctx);
        this.tokens = TokenManager.get(ctx);
    }

    public LiveData<NetworkResult<User>> register(RegisterRequest req) {
        MutableLiveData<NetworkResult<User>> live = new MutableLiveData<>(NetworkResult.loading());
        api.register(req).enqueue(new Callback<AuthResponse>() {
            @Override public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body()!=null) {
                    if (response.body().token != null) tokens.saveToken(response.body().token);
                    live.postValue(NetworkResult.success(response.body().user));
                } else {
                    live.postValue(NetworkResult.error(ErrorParser.parse(response)));
                }
            }
            @Override public void onFailure(Call<AuthResponse> call, Throwable t) {
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }

    public LiveData<NetworkResult<User>> login(LoginRequest req) {
        MutableLiveData<NetworkResult<User>> live = new MutableLiveData<>(NetworkResult.loading());
        api.login(req).enqueue(new Callback<AuthResponse>() {
            @Override public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body()!=null) {
                    if (response.body().token != null) tokens.saveToken(response.body().token);
                    live.postValue(NetworkResult.success(response.body().user));
                } else {
                    live.postValue(NetworkResult.error(ErrorParser.parse(response)));
                }
            }
            @Override public void onFailure(Call<AuthResponse> call, Throwable t) {
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }

    public LiveData<NetworkResult<String>> logout() {
        MutableLiveData<NetworkResult<String>> live = new MutableLiveData<>(NetworkResult.loading());
        api.logout().enqueue(new Callback<ApiMessageResponse>() {
            @Override public void onResponse(Call<ApiMessageResponse> call, Response<ApiMessageResponse> response) {
                tokens.clear();
                if (response.isSuccessful()) {
                    live.postValue(NetworkResult.success(response.body()!=null ? response.body().message : "Logged out"));
                } else {
                    live.postValue(NetworkResult.error(ErrorParser.parse(response)));
                }
            }
            @Override public void onFailure(Call<ApiMessageResponse> call, Throwable t) {
                tokens.clear();
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }

    public LiveData<NetworkResult<String>> forgot(ForgotRequest req) {
        MutableLiveData<NetworkResult<String>> live = new MutableLiveData<>(NetworkResult.loading());
        api.forgot(req).enqueue(new Callback<ApiMessageResponse>() {
            @Override public void onResponse(Call<ApiMessageResponse> call, Response<ApiMessageResponse> response) {
                if (response.isSuccessful()) live.postValue(NetworkResult.success(response.body()!=null ? response.body().message : "OK"));
                else live.postValue(NetworkResult.error(ErrorParser.parse(response)));
            }
            @Override public void onFailure(Call<ApiMessageResponse> call, Throwable t) {
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }

    public LiveData<NetworkResult<String>> reset(ResetRequest req) {
        MutableLiveData<NetworkResult<String>> live = new MutableLiveData<>(NetworkResult.loading());
        api.reset(req).enqueue(new Callback<ApiMessageResponse>() {
            @Override public void onResponse(Call<ApiMessageResponse> call, Response<ApiMessageResponse> response) {
                if (response.isSuccessful()) live.postValue(NetworkResult.success(response.body()!=null ? response.body().message : "OK"));
                else live.postValue(NetworkResult.error(ErrorParser.parse(response)));
            }
            @Override public void onFailure(Call<ApiMessageResponse> call, Throwable t) {
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }

    public LiveData<NetworkResult<String>> resendVerification() {
        MutableLiveData<NetworkResult<String>> live = new MutableLiveData<>(NetworkResult.loading());
        api.resendVerification().enqueue(new Callback<ApiMessageResponse>() {
            @Override public void onResponse(Call<ApiMessageResponse> call, Response<ApiMessageResponse> response) {
                if (response.isSuccessful()) live.postValue(NetworkResult.success(response.body()!=null ? response.body().message : "OK"));
                else live.postValue(NetworkResult.error(ErrorParser.parse(response)));
            }
            @Override public void onFailure(Call<ApiMessageResponse> call, Throwable t) {
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }

    public LiveData<NetworkResult<String>> verifyEmail(long id, String hash, String expires, String signature) {
        MutableLiveData<NetworkResult<String>> live = new MutableLiveData<>(NetworkResult.loading());
        api.verifyEmail(id, hash, expires, signature).enqueue(new Callback<ApiMessageResponse>() {
            @Override public void onResponse(Call<ApiMessageResponse> call, Response<ApiMessageResponse> response) {
                if (response.isSuccessful()) live.postValue(NetworkResult.success(response.body()!=null ? response.body().message : "OK"));
                else live.postValue(NetworkResult.error(ErrorParser.parse(response)));
            }
            @Override public void onFailure(Call<ApiMessageResponse> call, Throwable t) {
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }
}
