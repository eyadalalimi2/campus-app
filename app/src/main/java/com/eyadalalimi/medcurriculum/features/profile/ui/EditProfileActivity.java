package com.eyadalalimi.medcurriculum.features.profile.ui;

import androidx.lifecycle.ViewModelProvider;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.data.dto.profile.UpdateProfileRequest;
import com.eyadalalimi.medcurriculum.databinding.ActivityEditProfileBinding;
import com.eyadalalimi.medcurriculum.features.profile.vm.EditProfileViewModel;

public class EditProfileActivity extends BaseActivity {

    private ActivityEditProfileBinding b;
    private EditProfileViewModel vm;

    @Override protected int layoutId() { return R.layout.activity_edit_profile; }

    @Override protected void onReady() {
        b = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setProgressBar(b.progress);

        vm = new ViewModelProvider(this).get(EditProfileViewModel.class);

        b.btnSave.setOnClickListener(v -> {
            UpdateProfileRequest req = new UpdateProfileRequest(
                    txt(b.inputName), txt(b.inputPhone), txt(b.inputCountry),
                    parseLong(txt(b.inputUniversityId)), parseLong(txt(b.inputCollegeId)), parseLong(txt(b.inputMajorId))
            );
            showLoading(true);
            vm.save(req).observe(this, res -> {
                if (res.status == NetworkResult.Status.LOADING) return;
                showLoading(false);
                if (res.status == NetworkResult.Status.SUCCESS) { toast(b.getRoot(),"تم الحفظ"); finish(); }
                else toast(b.getRoot(), res.message);
            });
        });
    }

    private String txt(com.google.android.material.textfield.TextInputEditText e) {
        return e.getText()!=null ? e.getText().toString().trim() : "";
    }
    private Long parseLong(String s){ try { return Long.parseLong(s); } catch (Exception e) { return null; } }
}
