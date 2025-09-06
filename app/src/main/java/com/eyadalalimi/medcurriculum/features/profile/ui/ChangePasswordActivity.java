package com.eyadalalimi.medcurriculum.features.profile.ui;

import androidx.lifecycle.ViewModelProvider;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.databinding.ActivityChangePasswordBinding;
import com.eyadalalimi.medcurriculum.features.profile.vm.ChangePasswordViewModel;

public class ChangePasswordActivity extends BaseActivity {

    private ActivityChangePasswordBinding b;
    private ChangePasswordViewModel vm;

    @Override protected int layoutId() { return R.layout.activity_change_password; }

    @Override protected void onReady() {
        b = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setProgressBar(b.progress);

        vm = new ViewModelProvider(this).get(ChangePasswordViewModel.class);

        b.btnSave.setOnClickListener(v -> {
            String current = txt(b.inputCurrent);
            String next = txt(b.inputNew);
            String confirm = txt(b.inputConfirm);
            if (current.isEmpty() || next.isEmpty() || !next.equals(confirm)) {
                toast(b.getRoot(),"تحقق من الحقول"); return;
            }
            showLoading(true);
            vm.change(current, next).observe(this, res -> {
                if (res.status == NetworkResult.Status.LOADING) return;
                showLoading(false);
                if (res.status == NetworkResult.Status.SUCCESS) { toast(b.getRoot(),"تم التحديث"); finish(); }
                else toast(b.getRoot(), res.message);
            });
        });
    }
    private String txt(com.google.android.material.textfield.TextInputEditText e){ return e.getText()!=null?e.getText().toString():""; }
}
