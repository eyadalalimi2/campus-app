package com.eyadalalimi.medcurriculum.features.auth.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.data.dto.auth.ForgotRequest;
import com.eyadalalimi.medcurriculum.data.repo.AuthRepository;

public class ForgotViewModel extends AndroidViewModel {
    private final AuthRepository repo;

    public ForgotViewModel(@NonNull Application app) {
        super(app);
        repo = new AuthRepository(app);
    }

    public LiveData<NetworkResult<String>> send(String email) {
        return repo.forgot(new ForgotRequest(email));
    }
}
