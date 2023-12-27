package com.hanamarket.login.infrastructure.dto;

import com.hanamarket.login.application.command.LoginCommand;

public record LoginRequest(String email, String password) {

    public LoginCommand toCommand() {
        return new LoginCommand(email, password);
    }
}
