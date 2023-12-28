package com.hanamarket.login.application;

import com.hanamarket.config.security.JwtToken;
import com.hanamarket.login.application.command.LoginCommand;
import com.hanamarket.login.application.command.RegisterCommand;

public interface LoginApplicationService {

    void register(RegisterCommand registerCommand);

    JwtToken login(LoginCommand loginRequest);
}
