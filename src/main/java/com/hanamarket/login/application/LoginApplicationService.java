package com.hanamarket.login.application;

import com.hanamarket.login.application.command.LoginCommand;
import com.hanamarket.login.application.command.RegisterCommand;

public interface LoginApplicationService {

    void register(RegisterCommand registerCommand);

    void login(LoginCommand loginRequest);
}
