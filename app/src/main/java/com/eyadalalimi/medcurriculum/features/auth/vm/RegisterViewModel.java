package com.eyadalalimi.medcurriculum.features.auth.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.data.dto.auth.RegisterRequest;
import com.eyadalalimi.medcurriculum.data.repo.AuthRepository;
import com.eyadalalimi.medcurriculum.domain.model.User;

public class RegisterViewModel extends AndroidViewModel {

    private final AuthRepository repo;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repo = new AuthRepository(application);
    }

    public LiveData<NetworkResult<User>> register(RegisterRequest req) {
        return repo.register(req);
    }
}
