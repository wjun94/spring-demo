package com.study.spring_demo.common;

/**
 * 统一接口返回结果。
 *
 * @param <T> 返回数据类型
 */
public final class Result<T> {

    private static final int SUCCESS_CODE = 200;
    private static final int FAILURE_CODE = 500;

    private final int code;
    private final String message;
    private final T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, "操作成功", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, "操作成功", data);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(FAILURE_CODE, message, null);
    }

    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
