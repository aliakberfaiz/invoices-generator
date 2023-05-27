package com.company.reports.service;

import com.company.reports.vo.TokenVo;
import org.springframework.security.core.AuthenticationException;

public interface LoginService {
	public TokenVo login(String username, String password) throws AuthenticationException;
}
