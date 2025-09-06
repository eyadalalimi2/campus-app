package com.eyadalalimi.medcurriculum.core.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.eyadalalimi.medcurriculum.core.utils.Constants;

public class TokenManager {

    private static TokenManager instance;
    private final SharedPreferences prefs;

    private TokenManager(Context ctx) {
        this.prefs = SecurePrefs.provide(ctx, Constants.PREFS_FILE);
    }

    public static synchronized TokenManager get(Context ctx) {
        if (instance == null) instance = new TokenManager(ctx.getApplicationContext());
        return instance;
    }

    public void saveToken(String token) {
        prefs.edit().putString(Constants.KEY_TOKEN, token == null ? "" : token).apply();
    }

    public String getToken() {
        return prefs.getString(Constants.KEY_TOKEN, "");
    }

    public void clear() {
        prefs.edit().remove(Constants.KEY_TOKEN).apply();
    }
}
