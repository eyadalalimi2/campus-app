package com.eyadalalimi.medcurriculum.features.profile.ui;

import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.storage.TokenManager;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.core.utils.ImageUtils;
import com.eyadalalimi.medcurriculum.databinding.ActivityProfileBinding;
import com.eyadalalimi.medcurriculum.features.auth.ui.LoginActivity;
import com.eyadalalimi.medcurriculum.features.profile.vm.ProfileViewModel;
import com.eyadalalimi.medcurriculum.data.repo.AuthRepository;

public class ProfileActivity extends BaseActivity {

    private ActivityProfileBinding b;
    private ProfileViewModel vm;

    @Override protected int layoutId() { return R.layout.activity_profile; }

    @Override protected void onReady() {
        b = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setProgressBar(b.progress);

        vm = new ViewModelProvider(this).get(ProfileViewModel.class);

        b.btnEdit.setOnClickListener(v -> startActivity(new Intent(this, EditProfileActivity.class)));
        b.btnChangePassword.setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));
        b.btnAvatar.setOnClickListener(v -> startActivity(new Intent(this, AvatarActivity.class)));
        b.btnResendVerification.setOnClickListener(v ->
                startActivity(new Intent(this, com.eyadalalimi.medcurriculum.features.auth.ui.ResendVerificationActivity.class)));

        b.btnLogout.setOnClickListener(v -> {
            new AuthRepository(this).logout().observe(this, res -> {
                toast(b.getRoot(), "تم تسجيل الخروج");
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            });
        });

        load();
    }

    private void load() {
        showLoading(true);
        vm.load().observe(this, res -> {
            if (res.status == NetworkResult.Status.LOADING) return;
            showLoading(false);
            if (res.status == NetworkResult.Status.SUCCESS && res.data!=null) {
                var u = res.data;
                b.txtName.setText(u.name);
                b.txtEmail.setText(u.email);
                b.txtPhone.setText(u.phone);
                b.txtStudentNumber.setText(u.student_number);
                b.txtCountry.setText(u.country);
                b.txtIds.setText("U:" + u.university_id + " C:" + u.college_id + " M:" + u.major_id);
                ImageUtils.load(b.imgAvatar, u.profile_photo_url!=null ? u.profile_photo_url : u.profile_photo_path);
            } else {
                toast(b.getRoot(), res.message);
            }
        });
    }
}
