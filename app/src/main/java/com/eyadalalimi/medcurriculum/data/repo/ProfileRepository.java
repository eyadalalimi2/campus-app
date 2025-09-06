package com.eyadalalimi.medcurriculum.data.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eyadalalimi.medcurriculum.core.network.ApiClient;
import com.eyadalalimi.medcurriculum.core.network.ApiService;
import com.eyadalalimi.medcurriculum.core.network.ErrorParser;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.data.dto.profile.UpdatePasswordRequest;
import com.eyadalalimi.medcurriculum.data.dto.profile.UpdateProfileRequest;
import com.eyadalalimi.medcurriculum.data.dto.common.ApiMessageResponse;
import com.eyadalalimi.medcurriculum.domain.model.User;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    private final ApiService api;

    public ProfileRepository(Context ctx) {
        this.api = ApiClient.create(ctx);
    }

    public LiveData<NetworkResult<User>> me() {
        MutableLiveData<NetworkResult<User>> live = new MutableLiveData<>(NetworkResult.loading());
        api.me().enqueue(new Callback<User>() {
            @Override public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body()!=null) live.postValue(NetworkResult.success(response.body()));
                else live.postValue(NetworkResult.error(ErrorParser.parse(response)));
            }
            @Override public void onFailure(Call<User> call, Throwable t) {
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }

    public LiveData<NetworkResult<User>> getProfile() {
        MutableLiveData<NetworkResult<User>> live = new MutableLiveData<>(NetworkResult.loading());
        api.getProfile().enqueue(new Callback<User>() {
            @Override public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body()!=null) live.postValue(NetworkResult.success(response.body()));
                else live.postValue(NetworkResult.error(ErrorParser.parse(response)));
            }
            @Override public void onFailure(Call<User> call, Throwable t) {
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }

    public LiveData<NetworkResult<User>> updateProfile(UpdateProfileRequest req) {
        MutableLiveData<NetworkResult<User>> live = new MutableLiveData<>(NetworkResult.loading());
        api.updateProfile(req).enqueue(new Callback<User>() {
            @Override public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body()!=null) live.postValue(NetworkResult.success(response.body()));
                else live.postValue(NetworkResult.error(ErrorParser.parse(response)));
            }
            @Override public void onFailure(Call<User> call, Throwable t) {
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }

    public LiveData<NetworkResult<String>> updatePassword(UpdatePasswordRequest req) {
        MutableLiveData<NetworkResult<String>> live = new MutableLiveData<>(NetworkResult.loading());
        api.updatePassword(req).enqueue(new Callback<ApiMessageResponse>() {
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

    public LiveData<NetworkResult<User>> upsertAvatar(File imageFile) {
        MutableLiveData<NetworkResult<User>> live = new MutableLiveData<>(NetworkResult.loading());
        RequestBody rb = RequestBody.create(imageFile, MediaType.parse("image/*"));
        MultipartBody.Part part = MultipartBody.Part.createFormData("avatar", imageFile.getName(), rb);
        api.upsertAvatar(part).enqueue(new Callback<User>() {
            @Override public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body()!=null) live.postValue(NetworkResult.success(response.body()));
                else live.postValue(NetworkResult.error(ErrorParser.parse(response)));
            }
            @Override public void onFailure(Call<User> call, Throwable t) {
                live.postValue(NetworkResult.error(ErrorParser.parse(t)));
            }
        });
        return live;
    }

    public LiveData<NetworkResult<String>> deleteAvatar() {
        MutableLiveData<NetworkResult<String>> live = new MutableLiveData<>(NetworkResult.loading());
        api.deleteAvatar().enqueue(new Callback<ApiMessageResponse>() {
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
