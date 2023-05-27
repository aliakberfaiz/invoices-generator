package com.company.reports.service.impl;

import com.company.reports.exceptions.InvalidTokenException;
import com.company.reports.exceptions.TokenExpiredException;
import com.company.reports.exceptions.TokenRevokedException;
import com.company.reports.model.RefreshToken;
import com.company.reports.repo.RefreshTokenRepo;
import com.company.reports.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
@Service
public class TokenServiceImpl implements TokenService {
    private static final long EXPIRATION_TIME = 86400000; // 24 hours
    private static final String SECRET_KEY = "hiihsickueousctrjajdksfnkjasdjfjkansdfunBFSDUHUDSAIKHDAKLSALHDhLSKDSAKHJKASLSHDLASKDHJKALSDKXNCZ<XMCNSAIUDQWIEHIOPIWEIOPUWQOEIUPQWEUIPOQWUEOPWQUEselfdodlosp"; //TODO change with the App config

    @Autowired
    private RefreshTokenRepo refreshTokenRepository;

    public void storeRefreshToken(String username, String refreshToken) {
        RefreshToken token = new RefreshToken(null,username, refreshToken, LocalDateTime.now(),true);
        refreshTokenRepository.save(token);
    }

    public String generateJwtTokenFromRefreshToken(String refreshToken) {
        // Retrieve the refresh token from the repository
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken);
        if (token == null) {
            throw new InvalidTokenException("Invalid refresh token");
        }

        // Validate the refresh token and retrieve the associated username
        String username = validateRefreshToken(token);

        // Generate a new JWT token
        String jwtToken = generateJwtToken(username);

        return jwtToken;
    }

    private String validateRefreshToken(RefreshToken token) {
        if (token.isExpired()) {
            throw new TokenExpiredException("Refresh token has expired");
        }
        if (!token.isActive()) {
            throw new TokenRevokedException("Refresh token has been revoked");
        }
        return token.getUsername();
    }
    @Override
    public String generateJwtToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String generateRefreshToken(String username) {
        String refreshToken = UUID.randomUUID().toString();
        // TODO encrypt refreshToken before storing
        storeRefreshToken(username, refreshToken);

        return refreshToken;
    }
}
