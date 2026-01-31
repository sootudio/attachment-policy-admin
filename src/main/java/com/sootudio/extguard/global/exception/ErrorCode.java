package com.sootudio.extguard.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 공통
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", "잘못된 요청입니다."),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "서버 에러입니다."),

    // 고정 확장자
    FIXED_EXTENSION_NOT_FOUND(HttpStatus.NOT_FOUND, "FIXED_EXTENSION_NOT_FOUND", "해당 고정 확장자가 없습니다."),

    // 커스텀 확장자
    CUSTOM_EXTENSION_DUPLICATED(HttpStatus.CONFLICT, "CUSTOM_EXTENSION_DUPLICATED", "해당 커스텀 확장자가 이미 있습니다."),
    CUSTOM_EXTENSION_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "CUSTOM_EXTENSION_LIMIT_EXCEEDED", "커스텀 확장자는 최대 200개까지만 추가할 수 있습니다.");

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
