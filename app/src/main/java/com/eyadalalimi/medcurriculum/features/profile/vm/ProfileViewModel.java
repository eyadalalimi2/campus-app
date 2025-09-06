package com.eyadalalimi.medcurriculum.features.profile.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.data.repo.ProfileRepository;
import com.eyadalalimi.medcurriculum.domain.model.User;

public class ProfileViewModel extends AndroidViewModel {

    private final ProfileRepository repo;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        repo = new ProfileRepository(application);
    }

    public LiveData<NetworkResult<User>> load() {
        return repo.getProfile();
    }
}
