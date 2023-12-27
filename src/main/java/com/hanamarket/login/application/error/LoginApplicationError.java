package com.hanamarket.login.application.error;

import com.hanamarket.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LoginApplicationError implements ErrorCode {
    VALIDATE_EMAIL("LOGIN_A001", "이메일 형식에 맞지않습니다."),
    VALIDATE_PASSWORD("LOGIN_A002", "비밀번호는 최소 8자 이상입니다."),
    DUPLICATE_EMAIL_ERROR("LOGIN_A003", "이미 존재하는 email 입니다."),
    ;

    String code;
    String message;
    HttpStatus httpStatus;

    LoginApplicationError(String code, String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
