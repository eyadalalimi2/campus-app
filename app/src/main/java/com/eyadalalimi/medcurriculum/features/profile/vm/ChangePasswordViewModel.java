package com.eyadalalimi.medcurriculum.features.profile.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.data.dto.profile.UpdatePasswordRequest;
import com.eyadalalimi.medcurriculum.data.repo.ProfileRepository;

public class ChangePasswordViewModel extends AndroidViewModel {
    private final ProfileRepository repo;

    public ChangePasswordViewModel(@NonNull Application app) {
        super(app);
        repo = new ProfileRepository(app);
    }

    public LiveData<NetworkResult<String>> change(String current, String next) {
        return repo.updatePassword(new UpdatePasswordRequest(current, next));
    }
}
