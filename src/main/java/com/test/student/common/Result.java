package com.test.student.common;

import lombok.Data;

@Data
public class Result<T> {
    private String message;
    private T data;
    public  Result() {}
    public Result(String message, T data) {
        this.message = message;
        this.data = data;
    }
    public static <T> Result<T> success(T data) {
        return new Result<T>("success", data);
    }
    public static <T> Result<T> success(T data, String message) {
        return new Result<T>(message, data);
    }
    public static <T> Result<T>failed(String message) {
        return new Result<T>(message, null);
    }
    public static <T> Result<T> failed(String message, T data) {
        return new Result<T>(message, data);
    }
}
