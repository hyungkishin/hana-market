package com.hanamarket.login.infrastructure;

import com.hanamarket.login.application.LoginApplicationService;
import com.hanamarket.login.infrastructure.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginApplicationService loginApplicationService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        loginApplicationService.register(registerRequest.toCommand());
    }
}
