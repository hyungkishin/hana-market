package com.hanamarket.login.infrastructure.dto;

import com.hanamarket.login.application.command.LoginCommand;

import java.util.Arrays;
import java.util.List;

public record LoginRequest(String email, String password) {

    public LoginCommand toCommand() {
        return new LoginCommand(email, password, List.of("USER"));
    }
}
