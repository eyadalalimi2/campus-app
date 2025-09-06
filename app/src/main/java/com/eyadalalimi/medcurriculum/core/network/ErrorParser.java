package com.eyadalalimi.medcurriculum.core.network;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class ErrorParser {

    public static String parse(Throwable t) {
        return t.getMessage() != null ? t.getMessage() : "Network error";
    }

    public static String parse(Response<?> response) {
        try {
            ResponseBody rb = response.errorBody();
            if (rb == null) return "HTTP " + response.code();

            String body = rb.string();
            try {
                JsonObject obj = JsonParser.parseString(body).getAsJsonObject();
                if (obj.has("message")) return obj.get("message").getAsString();
                if (obj.has("error")) return obj.get("error").getAsString();
            } catch (Exception ignore) {}
            return "HTTP " + response.code();
        } catch (IOException e) {
            return "HTTP " + response.code();
        }
    }
}
