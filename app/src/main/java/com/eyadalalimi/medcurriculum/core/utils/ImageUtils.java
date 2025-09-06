package com.eyadalalimi.medcurriculum.core.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {

    public static void load(ImageView iv, String urlOrPath) {
        if (iv == null) return;
        String url = urlOrPath;
        if (url != null && !url.startsWith("http")) {
            url = Constants.BASE_FILES + (url.startsWith("/") ? url.substring(1) : url);
        }
        Glide.with(iv.getContext())
                .load(url)
                .centerCrop()
                .into(iv);
    }
}
