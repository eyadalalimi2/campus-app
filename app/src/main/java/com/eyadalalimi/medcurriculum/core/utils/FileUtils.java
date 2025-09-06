package com.eyadalalimi.medcurriculum.core.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public final class FileUtils {

    private FileUtils() {}

    /**
     * ينسخ محتوى الـ Uri إلى ملف داخل cacheDir ويعيد المسار المطلق.
     */
    public static String getPathFromUri(Context context, Uri uri) {
        String fileName = getFileName(context, uri);
        if (fileName == null || fileName.trim().isEmpty()) {
            fileName = "temp_upload";
        }
        File cacheFile = new File(context.getCacheDir(), fileName);
        copyUriToFile(context, uri, cacheFile);
        return cacheFile.getAbsolutePath();
    }

    private static String getFileName(Context context, Uri uri) {
        String result = null;
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver()
                    .query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (index >= 0) {
                    result = cursor.getString(index);
                }
            }
        } catch (Exception ignored) {
        } finally {
            if (cursor != null) cursor.close();
        }
        return result;
    }

    private static void copyUriToFile(Context context, Uri uri, File target) {
        try (InputStream in = context.getContentResolver().openInputStream(uri);
             FileOutputStream out = new FileOutputStream(target)) {
            if (in == null) return;
            byte[] buf = new byte[8192];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (Exception ignored) {
        }
    }
}
