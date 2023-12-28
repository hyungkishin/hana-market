package com.hanamarket.login.application;

import com.hanamarket.common.exception.MarketRuntimeException;
import com.hanamarket.config.security.JwtToken;
import com.hanamarket.login.application.command.RegisterCommand;
import com.hanamarket.login.infrastructure.dto.LoginRequest;
import com.hanamarket.login.infrastructure.dto.RegisterRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class LoginApplicationServiceTest {

    @Autowired
    LoginApplicationService loginApplicationService;

    @Test
    void 회원가입_정상_처리() {
        // given
        RegisterRequest registerRequest = new RegisterRequest(randomEmail() + "@naver.com", "testPassword", "어진이");
        RegisterCommand command = registerRequest.toCommand();

        // when
        loginApplicationService.register(command);
    }

    @Test
    void 회원가입_이메일_중복체크 () {
        // given
        RegisterRequest registerRequest = new RegisterRequest("eojin312@naver.com", "testPassword", "어진이");
        RegisterCommand command = registerRequest.toCommand();

        // when & then
        assertThatExceptionOfType(MarketRuntimeException.class).isThrownBy(() -> loginApplicationService.register(command));
    }

    @Test
    void 회원가입_이메일_validation체크 () {
        // given
        RegisterRequest registerRequest = new RegisterRequest("eojin312", "testPassword", "어진이");
        RegisterCommand command = registerRequest.toCommand();

        // when
        // then
        assertThatExceptionOfType(MarketRuntimeException.class).isThrownBy(() -> loginApplicationService.register(command));
    }

    @Test
    void 회원가입_비밀번호_validation체크 () {
        // given
        RegisterRequest registerRequest = new RegisterRequest("eojin312@naver.com", "test", "어진이");
        RegisterCommand command = registerRequest.toCommand();

        // when
        // then
        assertThatExceptionOfType(MarketRuntimeException.class).isThrownBy(() -> loginApplicationService.register(command));
    }


    @Test
    public void 로그인이_될까용 () {
        // given
        LoginRequest loginRequest = new LoginRequest("eojin312@naver.com", "testPassword");

        // when
        JwtToken loginToken = loginApplicationService.login(loginRequest.toCommand());

        // then
        System.out.println(loginToken);
        Assertions.assertNotNull(loginToken);
    }

    public String randomEmail() {
        int length = 5;
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
}
