package com.company.reports.repo;

import com.company.reports.model.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepo extends CrudRepository<RefreshToken,Long> {
    RefreshToken findByToken(String token);
}
