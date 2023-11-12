package com.hanamarket.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanamarket.common.annotation.ColumnDescription;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ErrorResponse {
    private static final Logger log = LoggerFactory.getLogger(ErrorResponse.class);

    @ColumnDescription("에러 코드")
    protected String code;

    @ColumnDescription("에러 메시지")
    protected String message;


    @ColumnDescription("상세 에러 메시지")
    protected String detailMessage;

    @ColumnDescription("추가적인 에러 정보 목록")
    private List<Object> detail;

    public ErrorResponse(ErrorCode ec) {
        this.code = ec.getCode();
        this.message = ec.getMessage();
    }

    public ErrorResponse(ErrorCode ec, List<Object> detail) {
        this((ErrorCode) ec, (String) null, detail);
    }

    public ErrorResponse(ErrorCode ec, String detailMessage) {
        this((ErrorCode) ec, detailMessage, (List) null);
    }

    public ErrorResponse(ErrorCode ec, String detailMessage, List<Object> detail) {
        this(ec.getCode(), ec.getMessage(), detailMessage, detail);
    }

    public ErrorResponse(String code, String message, List<Object> detail) {
        this(code, message, (String) null, detail);
    }

    public ErrorResponse(String code, String message, String detailMessage, List<Object> detail) {
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
        this.detail = detail;
    }

    public static ResponseEntity<ErrorResponse> buildResponse(Throwable e, ErrorCode ec) {
        log.error(ec.getHttpStatus().toString(), e);
        return ResponseEntity.status(ec.getHttpStatus()).body(new ErrorResponse(ec));
    }

    public static ResponseEntity<ErrorResponse> buildResponse(Throwable e, ErrorCode ec, Object detail) {
        log.error(ec.getHttpStatus().toString(), e);
        ErrorResponse errorResponse;
        if (detail instanceof List) {
            errorResponse = new ErrorResponse(ec, (List) detail);
        } else {
            errorResponse = new ErrorResponse(ec, Arrays.asList(detail));
        }

        return ResponseEntity.status(ec.getHttpStatus()).body(errorResponse);
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDetailMessage() {
        return this.detailMessage;
    }

    public List<Object> getDetail() {
        return this.detail;
    }

}