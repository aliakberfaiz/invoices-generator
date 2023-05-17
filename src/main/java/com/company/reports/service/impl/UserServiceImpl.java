package com.company.reports.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.reports.model.User;
import com.company.reports.model.UserInfo;
import com.company.reports.repo.UserRepo;
import com.company.reports.service.UserService;
import com.company.reports.vo.CreateUserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public Integer createUserWithDetails(CreateUserVo userInput) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String encryptedPassword = encoder.encode(userInput.getPassword());
	    
		User user = new User(null, userInput.getUsername(), userInput.getEmail(), encryptedPassword, true, null,
				LocalDateTime.now(), LocalDateTime.now());

		UserInfo userInfo = new UserInfo(null, userInput.getName(), userInput.getSurname(), userInput.getAge(),
				userInput.getAddress(), userInput.getPhoneNumber(), true, user, LocalDateTime.now(),
				LocalDateTime.now());
		userInfo.setUser(user);
		user.setUserInfo(userInfo);
		user = userRepo.save(user);
		return user.getId();
	}
	@Override
	public String getPasswordByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user.getPassword();
    }

}
