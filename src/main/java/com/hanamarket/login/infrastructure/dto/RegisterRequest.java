package com.hanamarket.login.infrastructure.dto;

import com.hanamarket.login.application.command.RegisterCommand;

public record RegisterRequest(String email, String password, String nickname) {
    public RegisterCommand toCommand() {
        return new RegisterCommand(email, password, nickname);
    }
}
