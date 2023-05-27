package com.company.reports.controller;

import com.company.reports.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.AuthenticationException;

import com.company.reports.service.LoginService;
import com.company.reports.vo.LoginVo;

import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

	
	
	@Autowired 
	LoginService loginService;
	
	@PostMapping
    public ResponseEntity<?> login(@RequestBody LoginVo loginVo) throws AuthenticationException {
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();

      TokenVo tokens =  loginService.login(username, password);
        return ResponseEntity.ok(tokens);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
	
	
}
