package com.eyadalalimi.medcurriculum.features.auth.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.data.repo.AuthRepository;

public class VerifyEmailViewModel extends AndroidViewModel {
    private final AuthRepository repo;

    public VerifyEmailViewModel(@NonNull Application app) {
        super(app);
        repo = new AuthRepository(app);
    }

    public LiveData<NetworkResult<String>> verify(long id, String hash, String expires, String signature) {
        return repo.verifyEmail(id, hash, expires, signature);
    }
}
