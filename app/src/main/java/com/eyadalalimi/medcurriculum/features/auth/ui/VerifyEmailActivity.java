package com.eyadalalimi.medcurriculum.features.auth.ui;

import android.net.Uri;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.databinding.ActivityVerifyEmailBinding;
import com.eyadalalimi.medcurriculum.features.auth.vm.VerifyEmailViewModel;

public class VerifyEmailActivity extends BaseActivity {

    private ActivityVerifyEmailBinding b;
    private VerifyEmailViewModel vm;

    @Override protected int layoutId() { return R.layout.activity_verify_email; }

    @Override protected void onReady() {
        b = ActivityVerifyEmailBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setProgressBar(b.progress);

        vm = new ViewModelProvider(this).get(VerifyEmailViewModel.class);

        Uri uri = getIntent()!=null ? getIntent().getData() : null;
        if (uri == null) { toast(b.getRoot(),"رابط غير صالح"); finish(); return; }

        // نتوقع شكل /auth/verify-email/{id}/{hash}?expires=...&signature=...
        try {
            String path = uri.getPath(); // قد يكون /auth/verify-email/33/abc123...
            String[] seg = path != null ? path.split("/") : new String[0];
            long id = Long.parseLong(seg[seg.length-2]);
            String hash = seg[seg.length-1];
            String expires = uri.getQueryParameter("expires");
            String signature = uri.getQueryParameter("signature");

            showLoading(true);
            vm.verify(id, hash, expires, signature).observe(this, res -> {
                if (res.status == NetworkResult.Status.LOADING) return;
                showLoading(false);
                if (res.status == NetworkResult.Status.SUCCESS) toast(b.getRoot(), "تم التحقق من البريد");
                else toast(b.getRoot(), res.message);
            });
        } catch (Exception e) {
            toast(b.getRoot(),"لا يمكن تحليل الرابط");
            finish();
        }
    }
}
