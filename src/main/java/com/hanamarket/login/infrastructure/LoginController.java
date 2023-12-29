package com.hanamarket.login.infrastructure;

import com.hanamarket.config.security.JwtToken;
import com.hanamarket.login.application.LoginApplicationService;
import com.hanamarket.login.infrastructure.dto.LoginRequest;
import com.hanamarket.login.infrastructure.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    @PostMapping("/login")
    public JwtToken login(@RequestBody LoginRequest loginRequest) {
        return loginApplicationService.login(loginRequest.toCommand());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
    }
}
