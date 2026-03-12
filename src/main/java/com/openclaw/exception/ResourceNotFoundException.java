package com.openclaw.exception;

/**
 * 资源未找到异常
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-12
 */
public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String resource, Long id) {
        super(404, resource + " not found with id: " + id);
    }

    public ResourceNotFoundException(String message) {
        super(404, message);
    }
}
