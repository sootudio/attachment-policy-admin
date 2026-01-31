package com.sootudio.extguard.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 공통
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", "invalid request"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "internal server error"),

    // 고정 확장자
    FIXED_EXTENSION_NOT_FOUND(HttpStatus.NOT_FOUND, "FIXED_EXTENSION_NOT_FOUND", "fixed extension not found");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus status() { return status; }
    public String code() { return code; }
    public String message() { return message; }
}
