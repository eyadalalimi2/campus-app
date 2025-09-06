package com.eyadalalimi.medcurriculum.features.auth.ui;

import androidx.lifecycle.ViewModelProvider;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.databinding.ActivityResetPasswordBinding;
import com.eyadalalimi.medcurriculum.features.auth.vm.ResetViewModel;

public class ResetPasswordActivity extends BaseActivity {

    private ActivityResetPasswordBinding b;
    private ResetViewModel vm;

    @Override protected int layoutId() { return R.layout.activity_reset_password; }

    @Override protected void onReady() {
        b = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setProgressBar(b.progress);

        vm = new ViewModelProvider(this).get(ResetViewModel.class);

        b.btnReset.setOnClickListener(v -> {
            String token = txt(b.inputToken);
            String email = txt(b.inputEmail);
            String pass = txt(b.inputPassword);
            if (token.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                toast(b.getRoot(),"أكمل البيانات"); return;
            }
            showLoading(true);
            vm.reset(token, email, pass).observe(this, res -> {
                if (res.status == NetworkResult.Status.LOADING) return;
                showLoading(false);
                if (res.status == NetworkResult.Status.SUCCESS) { toast(b.getRoot(),"تم"); finish(); }
                else toast(b.getRoot(), res.message);
            });
        });
    }

    private String txt(com.google.android.material.textfield.TextInputEditText e) {
        return e.getText()!=null ? e.getText().toString().trim() : "";
    }
}
