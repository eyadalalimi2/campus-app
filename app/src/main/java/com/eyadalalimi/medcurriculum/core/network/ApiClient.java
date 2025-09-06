package com.eyadalalimi.medcurriculum.core.network;

import android.content.Context;

import com.eyadalalimi.medcurriculum.core.storage.TokenManager;
import com.eyadalalimi.medcurriculum.core.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;

    public static ApiService create(Context ctx) {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(TokenManager.get(ctx)))
                    .addInterceptor(logging)
                    .connectTimeout(Constants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .readTimeout(Constants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .writeTimeout(Constants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
