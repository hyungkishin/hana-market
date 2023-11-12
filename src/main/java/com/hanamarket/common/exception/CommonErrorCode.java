package com.hanamarket.common.exception;

import com.hanamarket.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    SERVER_ERROR("E_000", "서버 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST("E_001", "잘못된 요청"),
    INVALID_REQUEST_PARAM("E_002", "요청 파라미터 오류"),
    UNHANDLED_ERROR("E_003", "서버 오류. 확인 요청 필요", HttpStatus.INTERNAL_SERVER_ERROR);

    String code;
    String message;
    HttpStatus httpStatus;

    CommonErrorCode(String code, String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
