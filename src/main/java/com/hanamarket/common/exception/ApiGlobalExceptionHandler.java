package com.hanamarket.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ApiGlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleException(MarketRuntimeException e) {
        StringBuilder logMsg = new StringBuilder().append("message: ").append(e.getMessage());
        ;
        if (StringUtils.hasText(e.getDetailMessage())) {
            logMsg.append("\ndetailMessage: ").append(e.getDetailMessage());
        }
        if (!CollectionUtils.isEmpty(e.getDetail())) {
            logMsg.append("\ndetail: ").append(e.getDetail());
        }

        // 5xx 인 경우와 그 외의 경우에 따라 error / info 로깅
        if (e.getErrorCode().getHttpStatus().is5xxServerError()) {
            log.error(logMsg.toString(), e);
        } else {
            log.info(logMsg.toString(), e);
        }

        ErrorCode ec = e.getErrorCode();
        return ResponseEntity
                .status(ec.getHttpStatus())
                .body(new ErrorResponse(ec, e.getDetailMessage(), e.getDetail()));
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ErrorResponse> handleException(NullPointerException e) {
        String message = e.getMessage();
        if (!StringUtils.hasText(message)) {
            message = e.getClass().getName();
        }

        return ErrorResponse.buildResponse(e, CommonErrorCode.SERVER_ERROR, message);
    }

    // TODO 보완 할것
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        MarketRuntimeException hre = new MarketRuntimeException(CommonErrorCode.SERVER_ERROR, e.getMessage());
        return handleException(e);
    }

}
