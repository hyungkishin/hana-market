package com.hanamarket.common.exception.message;

import com.hanamarket.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductErrorCode implements ErrorCode {

    NOT_FOUND_PRODUCT("존재하지 않는 상품");

    private static final String title = "상품";

    String code;

    String message;

    HttpStatus httpStatus;

    ProductErrorCode(String message) {
        this(message, HttpStatus.BAD_GATEWAY);
    }

    ProductErrorCode(String message, HttpStatus status) {
        this(title, message, status);
    }

}
