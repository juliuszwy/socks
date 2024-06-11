package com.scoks.order.exception;

import lombok.Data;

@Data
public class Result<T> {
    private T data;
    private Integer code;
    private String message;

    private Result(T data,Integer code,String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static<T> Result<T> success(T data) {
        return new Result<>(data,ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage());
    }

    public static<T> Result<T> error(ResultStatus status) {
        return new Result<>(null,status.getCode(),status.getMessage());
    }
}