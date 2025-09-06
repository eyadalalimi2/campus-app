package com.eyadalalimi.medcurriculum.core.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public abstract class BaseActivity extends AppCompatActivity {

    protected ProgressBar progressBar;

    @LayoutRes
    protected abstract int layoutId();

    protected abstract void onReady();

    protected void setProgressBar(ProgressBar bar) {
        this.progressBar = bar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        onReady();
    }

    protected void showLoading(boolean show) {
        if (progressBar == null) return;
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    protected void toast(View anchor, String message) {
        if (anchor == null) anchor = getWindow().getDecorView().getRootView();
        Snackbar.make(anchor, message == null ? "حدث خطأ" : message, Snackbar.LENGTH_LONG).show();
    }
}
