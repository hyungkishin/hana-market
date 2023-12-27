package com.hanamarket.login.application.impl;

import com.hanamarket.common.exception.MarketRuntimeException;
import com.hanamarket.login.application.LoginApplicationService;
import com.hanamarket.login.application.command.RegisterCommand;
import com.hanamarket.login.application.error.LoginApplicationError;
import com.hanamarket.login.domain.Member;
import com.hanamarket.login.domain.repository.MemberRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginApplicationServiceImpl implements LoginApplicationService {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z]).{8,}$";

    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

    private final MemberRepository memberRepository;


    @Override
    public void register(RegisterCommand registerCommand) {
        if (!isValidEmail(registerCommand.email())) {
            throw new MarketRuntimeException(LoginApplicationError.VALIDATE_EMAIL);
        }

        if (!isValidPassword(registerCommand.password())) {
            throw new MarketRuntimeException(LoginApplicationError.VALIDATE_PASSWORD);
        }

        Member member = Member.builder()
                .email(registerCommand.email())
                .password(registerCommand.password())
                .nickname(registerCommand.nickname())
                .build();

        memberRepository.save(member);
    }

    private boolean isValidEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }

        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }

        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }
}
