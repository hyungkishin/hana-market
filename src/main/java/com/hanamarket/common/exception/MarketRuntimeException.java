package com.hanamarket.common.exception;

import java.util.Arrays;
import java.util.List;

public class MarketRuntimeException extends RuntimeException {
    protected final ErrorCode errorCode;
    protected String detailMessage;
    protected List<Object> detail;

    public MarketRuntimeException(ErrorCode errorCode, Object detail) {
        this(errorCode, (String) null, detail);
    }

    public MarketRuntimeException(ErrorCode errorCode, String detailMessage, Object detail) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
        if (detail != null) {
            if (detail instanceof List) {
                this.detail = (List) detail;
            } else {
                this.detail = Arrays.asList(detail);
            }

        }
    }

    public MarketRuntimeException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String getDetailMessage() {
        return this.detailMessage;
    }

    public List<Object> getDetail() {
        return this.detail;
    }

}