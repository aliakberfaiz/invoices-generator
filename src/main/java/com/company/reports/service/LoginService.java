package com.company.reports.service;

import org.springframework.security.core.AuthenticationException;

public interface LoginService {
	public String login(String username, String password) throws AuthenticationException;
}
