package com.hanamarket.login.application;

import com.hanamarket.login.application.command.RegisterCommand;

public interface LoginApplicationService {

    void register(RegisterCommand registerCommand);
}
