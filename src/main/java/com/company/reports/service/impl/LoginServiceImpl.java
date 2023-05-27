package com.company.reports.service.impl;

import java.util.Date;

import javax.crypto.SecretKey;

import antlr.Token;
import com.company.reports.service.TokenService;
import com.company.reports.vo.TokenVo;
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


    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @SuppressWarnings("serial")
	@Override
    public TokenVo login(String username, String password) throws AuthenticationException {
        try {
            String encodedPassword = userService.getPasswordByUsername(username);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	    if(encoder.matches(password, encodedPassword)){
                String token = tokenService.generateJwtToken(username);
                String refreshToken = tokenService.generateRefreshToken(username);
                return  new TokenVo(token, refreshToken);
            }
    	    else
    	    	throw new AuthenticationException("Invalid password") {};
        } catch (UsernameNotFoundException ex) {
            throw new AuthenticationException("Invalid username or password") {};
        }
    }


}

