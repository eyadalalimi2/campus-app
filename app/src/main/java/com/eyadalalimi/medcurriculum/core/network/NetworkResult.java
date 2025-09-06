package com.eyadalalimi.medcurriculum.core.network;

public class NetworkResult<T> {
    public enum Status { LOADING, SUCCESS, ERROR }

    public final Status status;
    public final T data;
    public final String message;

    private NetworkResult(Status status, T data, String message) {
        this.status = status; this.data = data; this.message = message;
    }

    public static <T> NetworkResult<T> loading() { return new NetworkResult<>(Status.LOADING, null, null); }
    public static <T> NetworkResult<T> success(T data) { return new NetworkResult<>(Status.SUCCESS, data, null); }
    public static <T> NetworkResult<T> error(String msg) { return new NetworkResult<>(Status.ERROR, null, msg); }
}
