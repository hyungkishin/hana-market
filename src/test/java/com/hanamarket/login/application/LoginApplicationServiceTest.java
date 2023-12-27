package com.hanamarket.login.application;

import com.hanamarket.login.application.command.RegisterCommand;
import com.hanamarket.login.domain.Member;
import com.hanamarket.login.infrastructure.dto.RegisterRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginApplicationServiceTest {

    @Autowired
    LoginApplicationService loginApplicationService;

    @Test
    void 회원가입_정상_처리() {
        // given
        RegisterRequest registerRequest = new RegisterRequest("eojin312@naver.com", "testPassword", "어진이");
        RegisterCommand command = registerRequest.toCommand();

        // when
        loginApplicationService.register(command);
    }

    @Test
    public void 회원가입_이메일_validation체크 () {
        // given
        RegisterRequest registerRequest = new RegisterRequest("eojin312", "testPassword", "어진이");
        RegisterCommand command = registerRequest.toCommand();

        // when
        // then
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> loginApplicationService.register(command));
    }

    @Test
    public void 회원가입_비밀번호_validation체크 () {
        // given
        RegisterRequest registerRequest = new RegisterRequest("eojin312@naver.com", "test", "어진이");
        RegisterCommand command = registerRequest.toCommand();

        // when
        // then
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> loginApplicationService.register(command));
    }
}
