package com.sootudio.extguard.global.exception;

import com.sootudio.extguard.global.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApi(ApiException ex) {
        ErrorCode ec = ex.errorCode();
        return ResponseEntity
                .status(ec.status())
                .body(ApiResponse.error(ec.status(), ec.code(), ec.message()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().isEmpty()
                ? "validation error"
                : ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage(); // 첫 에러만 출력

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(org.springframework.http.HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", msg));
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotReadable(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(org.springframework.http.HttpStatus.BAD_REQUEST, "INVALID_REQUEST", "invalid json body"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneral(Exception ex) {
        log.error("Unhandled exception", ex);
        ErrorCode ec = ErrorCode.INTERNAL_ERROR;
        return ResponseEntity
                .status(ec.status())
                .body(ApiResponse.error(ec.status(), ec.code(), ec.message()));
    }
}
