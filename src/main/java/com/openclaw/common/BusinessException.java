package com.openclaw.common;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private Integer code;
    private String message;

    public BusinessException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
    }
}