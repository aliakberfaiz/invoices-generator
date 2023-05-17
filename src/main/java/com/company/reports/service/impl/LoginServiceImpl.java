package com.company.reports.service.impl;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.reports.service.LoginService;
import com.company.reports.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class LoginServiceImpl implements LoginService {
    private static final long EXPIRATION_TIME = 86400000; // 24 hours
    private static final String SECRET_KEY = "hiihsickueousctrjajdksfnkjasdjfjkansdfunBFSDUHUDSAIKHDAKLSALHDhLSKDSAKHJKASLSHDLASKDHJKALSDKXNCZ<XMCNSAIUDQWIEHIOPIWEIOPUWQOEIUPQWEUIPOQWUEOPWQUEselfdodlosp"; //TODO change with the App config


    @Autowired
    private UserService userService;

    @SuppressWarnings("serial")
	@Override
    public String login(String username, String password) throws AuthenticationException {
        try {
            String encodedPassword = userService.getPasswordByUsername(username);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	    if(encoder.matches(password, encodedPassword))
    	    	return generateJwtToken(username);
    	    else
    	    	throw new AuthenticationException("Invalid password") {};
        } catch (UsernameNotFoundException ex) {
            throw new AuthenticationException("Invalid username or password") {};
        }
    }

    private String generateJwtToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
    }
}

