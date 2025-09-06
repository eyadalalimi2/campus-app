package com.eyadalalimi.medcurriculum.features.profile.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.data.repo.ProfileRepository;
import com.eyadalalimi.medcurriculum.domain.model.User;

import java.io.File;

public class AvatarViewModel extends AndroidViewModel {
    private final ProfileRepository repo;

    public AvatarViewModel(@NonNull Application app) {
        super(app);
        repo = new ProfileRepository(app);
    }

    public LiveData<NetworkResult<User>> upload(File file) {
        return repo.upsertAvatar(file);
    }

    public LiveData<NetworkResult<String>> deleteAvatar() {
        return repo.deleteAvatar();
    }
}
