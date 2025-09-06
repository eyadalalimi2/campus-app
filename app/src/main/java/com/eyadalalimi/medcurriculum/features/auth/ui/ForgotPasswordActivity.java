package com.eyadalalimi.medcurriculum.features.auth.ui;

import androidx.lifecycle.ViewModelProvider;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.databinding.ActivityForgotPasswordBinding;
import com.eyadalalimi.medcurriculum.features.auth.vm.ForgotViewModel;

public class ForgotPasswordActivity extends BaseActivity {

    private ActivityForgotPasswordBinding b;
    private ForgotViewModel vm;

    @Override protected int layoutId() { return R.layout.activity_forgot_password; }

    @Override protected void onReady() {
        b = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setProgressBar(b.progress);

        vm = new ViewModelProvider(this).get(ForgotViewModel.class);

        b.btnSend.setOnClickListener(v -> {
            String email = b.inputEmail.getText()!=null ? b.inputEmail.getText().toString().trim() : "";
            if (email.isEmpty()) { toast(b.getRoot(),"أدخل البريد"); return; }
            showLoading(true);
            vm.send(email).observe(this, res -> {
                if (res.status == NetworkResult.Status.LOADING) return;
                showLoading(false);
                if (res.status == NetworkResult.Status.SUCCESS) toast(b.getRoot(), "تم الإرسال");
                else toast(b.getRoot(), res.message);
            });
        });
    }
}
