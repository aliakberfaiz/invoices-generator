package com.company.reports.service;

import com.company.reports.vo.CreateUserVo;

public interface UserService {
	public Integer createUserWithDetails(CreateUserVo userInput);
	public String getPasswordByUsername(String username) ;
}
