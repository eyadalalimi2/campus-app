package com.eyadalalimi.medcurriculum.features.auth.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.data.dto.auth.LoginRequest;
import com.eyadalalimi.medcurriculum.data.repo.AuthRepository;
import com.eyadalalimi.medcurriculum.domain.model.User;

public class LoginViewModel extends AndroidViewModel {

    private final AuthRepository repo;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repo = new AuthRepository(application);
    }

    public LiveData<NetworkResult<User>> login(String email, String password) {
        return repo.login(new LoginRequest(email, password, "Android"));
    }
}
