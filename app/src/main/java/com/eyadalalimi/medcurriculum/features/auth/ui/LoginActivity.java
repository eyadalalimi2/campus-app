package com.eyadalalimi.medcurriculum.features.auth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.storage.TokenManager;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.databinding.ActivityLoginBinding;
import com.eyadalalimi.medcurriculum.features.auth.vm.LoginViewModel;
import com.eyadalalimi.medcurriculum.features.profile.ui.ProfileActivity;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding b;
    private LoginViewModel vm;

    @Override
    protected int layoutId() { return R.layout.activity_login; }

    @Override
    protected void onReady() {
        b = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setProgressBar(b.progress);

        vm = new ViewModelProvider(this).get(LoginViewModel.class);

        b.btnLogin.setOnClickListener(v -> {
            String email = b.inputEmail.getText()!=null ? b.inputEmail.getText().toString().trim() : "";
            String pass  = b.inputPassword.getText()!=null ? b.inputPassword.getText().toString() : "";
            if (email.isEmpty() || pass.isEmpty()) {
                toast(b.getRoot(), "أدخل البريد وكلمة المرور");
                return;
            }
            showLoading(true);
            vm.login(email, pass).observe(this, res -> {
                if (res.status == NetworkResult.Status.LOADING) return;
                showLoading(false);
                if (res.status == NetworkResult.Status.SUCCESS) {
                    startActivity(new Intent(this, ProfileActivity.class));
                    finish();
                } else {
                    toast(b.getRoot(), res.message);
                }
            });
        });

        b.linkRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));

        b.linkForgot.setOnClickListener(v ->
                startActivity(new Intent(this, ForgotPasswordActivity.class)));
    }
}
