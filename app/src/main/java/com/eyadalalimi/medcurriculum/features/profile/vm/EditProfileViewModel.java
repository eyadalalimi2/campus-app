package com.eyadalalimi.medcurriculum.features.profile.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.data.dto.profile.UpdateProfileRequest;
import com.eyadalalimi.medcurriculum.data.repo.ProfileRepository;
import com.eyadalalimi.medcurriculum.domain.model.User;

public class EditProfileViewModel extends AndroidViewModel {
    private final ProfileRepository repo;

    public EditProfileViewModel(@NonNull Application app) {
        super(app);
        repo = new ProfileRepository(app);
    }

    public LiveData<NetworkResult<User>> save(UpdateProfileRequest req) {
        return repo.updateProfile(req);
    }
}
