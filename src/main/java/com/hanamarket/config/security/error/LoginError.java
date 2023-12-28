package com.hanamarket.config.security.error;

import com.hanamarket.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LoginError implements ErrorCode {
    JWT_TOKEN_ERROR("LOGIN_001", "권한 정보가 없는 토큰입니다."),
    ;

    String code;
    String message;
    HttpStatus httpStatus;

    LoginError(String code, String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
