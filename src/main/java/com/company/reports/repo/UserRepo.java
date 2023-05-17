package com.company.reports.repo;

import org.springframework.data.repository.CrudRepository;

import com.company.reports.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
	
	  User findByUsername(String username);

}
