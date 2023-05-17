package com.company.reports.repo;

import org.springframework.data.repository.CrudRepository;

import com.company.reports.model.UserInfo;

public interface UserDetailsRepo extends CrudRepository<UserInfo,Integer> {

}
