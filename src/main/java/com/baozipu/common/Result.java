package com.baozipu.common;
import lombok.Data;

@Data
public class Result {
    private int code;
    private String message;
    private Object data;
    private long timestamp;

    public Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    public static Result success(String message) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage(message);
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }
}