package com.company.reports.service;

public interface TokenService {
    public String generateJwtToken(String username);
    public String generateRefreshToken(String username);
}
