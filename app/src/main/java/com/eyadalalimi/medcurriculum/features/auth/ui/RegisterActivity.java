package com.eyadalalimi.medcurriculum.features.auth.ui;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.data.dto.auth.RegisterRequest;
import com.eyadalalimi.medcurriculum.databinding.ActivityRegisterBinding;
import com.eyadalalimi.medcurriculum.features.auth.vm.RegisterViewModel;

public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding b;
    private RegisterViewModel vm;

    @Override
    protected int layoutId() { return R.layout.activity_register; }

    @Override
    protected void onReady() {
        b = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setProgressBar(b.progress);

        vm = new ViewModelProvider(this).get(RegisterViewModel.class);

        b.btnRegister.setOnClickListener(v -> {
            String name = txt(b.inputName);
            String email = txt(b.inputEmail);
            String pass = txt(b.inputPassword);

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                toast(b.getRoot(), "أكمل الحقول المطلوبة");
                return;
            }

            RegisterRequest req = new RegisterRequest(
                    name, email, pass,
                    txt(b.inputPhone),
                    txt(b.inputStudentNumber),
                    txt(b.inputCountry),
                    parseLong(b.inputUniversityId.getText()),
                    parseLong(b.inputCollegeId.getText()),
                    parseLong(b.inputMajorId.getText())
            );

            showLoading(true);
            vm.register(req).observe(this, res -> {
                if (res.status == NetworkResult.Status.LOADING) return;
                showLoading(false);
                if (res.status == NetworkResult.Status.SUCCESS) {
                    toast(b.getRoot(), "تم التسجيل - تحقق بريدك");
                    finish();
                } else {
                    toast(b.getRoot(), res.message);
                }
            });
        });
    }

    private String txt(com.google.android.material.textfield.TextInputEditText e) {
        return e.getText()!=null ? e.getText().toString().trim() : "";
    }

    private Long parseLong(CharSequence cs) {
        try { return Long.parseLong(cs.toString()); } catch (Exception e) { return null; }
    }
}
