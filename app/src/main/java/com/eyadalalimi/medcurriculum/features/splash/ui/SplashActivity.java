package com.eyadalalimi.medcurriculum.features.splash.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.storage.TokenManager;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.features.auth.ui.LoginActivity;
import com.eyadalalimi.medcurriculum.features.profile.ui.ProfileActivity;

public class SplashActivity extends BaseActivity {

    @Override protected int layoutId() { return R.layout.activity_splash; }
    @Override protected void onReady() {
        // لا يوجد ProgressBar هنا
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            String token = TokenManager.get(this).getToken();
            if (token != null && !token.isEmpty()) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
            finish();
        }, 800);
    }
}
