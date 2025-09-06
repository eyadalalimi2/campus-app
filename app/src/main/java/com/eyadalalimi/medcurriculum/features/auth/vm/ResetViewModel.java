package com.eyadalalimi.medcurriculum.features.auth.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.data.dto.auth.ResetRequest;
import com.eyadalalimi.medcurriculum.data.repo.AuthRepository;

public class ResetViewModel extends AndroidViewModel {
    private final AuthRepository repo;

    public ResetViewModel(@NonNull Application app) {
        super(app);
        repo = new AuthRepository(app);
    }

    public LiveData<NetworkResult<String>> reset(String token, String email, String newPass) {
        return repo.reset(new ResetRequest(token, email, newPass));
    }
}
