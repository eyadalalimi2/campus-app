package com.eyadalalimi.medcurriculum.core.utils;

import android.Manifest;
import android.os.Build;

public final class PermissionUtils {
    private PermissionUtils(){}

    public static String[] readImagePermissions() {
        if (Build.VERSION.SDK_INT >= 33) {
            return new String[]{ Manifest.permission.READ_MEDIA_IMAGES };
        } else {
            return new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE };
        }
    }
}
