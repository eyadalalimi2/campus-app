package com.eyadalalimi.medcurriculum.features.auth.ui;

import androidx.lifecycle.ViewModelProvider;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.data.repo.AuthRepository;
import com.eyadalalimi.medcurriculum.databinding.ActivityResendVerificationBinding;

public class ResendVerificationActivity extends BaseActivity {

    private ActivityResendVerificationBinding b;
    private AuthRepository repo;

    @Override protected int layoutId() { return R.layout.activity_resend_verification; }

    @Override protected void onReady() {
        b = ActivityResendVerificationBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setProgressBar(b.progress);

        repo = new AuthRepository(this);

        b.btnResend.setOnClickListener(v -> {
            showLoading(true);
            repo.resendVerification().observe(this, res -> {
                if (res.status == NetworkResult.Status.LOADING) return;
                showLoading(false);
                if (res.status == NetworkResult.Status.SUCCESS) {
                    toast(b.getRoot(), getString(R.string.verify_email_done));
                    finish();
                } else {
                    toast(b.getRoot(), res.message);
                }
            });
        });
    }
}
