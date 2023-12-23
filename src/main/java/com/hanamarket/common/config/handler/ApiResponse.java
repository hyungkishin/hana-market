package com.hanamarket.common.config.handler;

import lombok.Getter;

@Getter
public class ApiResponse {

    private Object data;

    public ApiResponse(Object data) {
        this.data = data;
    }

}
