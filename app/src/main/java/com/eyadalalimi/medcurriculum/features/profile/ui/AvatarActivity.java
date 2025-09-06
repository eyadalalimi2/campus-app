package com.eyadalalimi.medcurriculum.features.profile.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;
import android.net.Uri;

import com.eyadalalimi.medcurriculum.R;
import com.eyadalalimi.medcurriculum.core.network.NetworkResult;
import com.eyadalalimi.medcurriculum.core.ui.BaseActivity;
import com.eyadalalimi.medcurriculum.core.utils.FileUtils;
import com.eyadalalimi.medcurriculum.core.utils.ImageUtils;
import com.eyadalalimi.medcurriculum.core.utils.PermissionUtils;
import com.eyadalalimi.medcurriculum.databinding.ActivityAvatarBinding;
import com.eyadalalimi.medcurriculum.features.profile.vm.AvatarViewModel;

import java.io.File;

public class AvatarActivity extends BaseActivity {

    private ActivityAvatarBinding b;
    private AvatarViewModel vm;
    private File pickedFile;

    private final ActivityResultLauncher<String> picker = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    String path = FileUtils.getPathFromUri(this, uri);
                    pickedFile = new File(path);
                    ImageUtils.load(b.imgPreview, uri.toString());
                }
            }
    );

    @Override protected int layoutId() { return R.layout.activity_avatar; }

    @Override protected void onReady() {
        b = ActivityAvatarBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setProgressBar(b.progress);

        vm = new ViewModelProvider(this).get(AvatarViewModel.class);

        b.btnPick.setOnClickListener(v -> requestPick());

        b.btnUpload.setOnClickListener(v -> {
            if (pickedFile == null || !pickedFile.exists()) { toast(b.getRoot(), getString(R.string.choose_image_first)); return; }
            showLoading(true);
            vm.upload(pickedFile).observe(this, res -> {
                if (res.status == NetworkResult.Status.LOADING) return;
                showLoading(false);
                if (res.status == NetworkResult.Status.SUCCESS) { toast(b.getRoot(), getString(R.string.updated)); finish(); }
                else toast(b.getRoot(), res.message);
            });
        });

        b.btnDelete.setOnClickListener(v -> {
            showLoading(true);
            vm.deleteAvatar().observe(this, res -> {
                if (res.status == NetworkResult.Status.LOADING) return;
                showLoading(false);
                if (res.status == NetworkResult.Status.SUCCESS) { toast(b.getRoot(), getString(R.string.deleted)); finish(); }
                else toast(b.getRoot(), res.message);
            });
        });
    }

    private void requestPick() {
        String[] perms = PermissionUtils.readImagePermissions();
        boolean granted = true;
        for (String p : perms) {
            granted &= ActivityCompat.checkSelfPermission(this, p) == PackageManager.PERMISSION_GRANTED;
        }
        if (granted) {
            picker.launch("image/*");
        } else {
            requestPermissions(perms, 1010);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1010) {
            boolean ok = true;
            for (int r : grantResults) ok &= (r == PackageManager.PERMISSION_GRANTED);
            if (ok) picker.launch("image/*");
            else toast(b.getRoot(), getString(R.string.error_generic));
        }
    }
}
