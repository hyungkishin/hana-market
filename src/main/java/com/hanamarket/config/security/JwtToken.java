package com.hanamarket.config.security;

public record JwtToken(String grantType, String accessToken, String refreshToken) {
}
