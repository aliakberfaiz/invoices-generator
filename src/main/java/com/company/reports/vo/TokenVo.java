package com.company.reports.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenVo {
    private String token;
    private String refreshToken;
}
