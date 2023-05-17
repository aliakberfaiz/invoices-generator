package com.company.reports.vo;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserVo implements Serializable {

	private static final long serialVersionUID = 3539012778946529142L;

	private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Integer age;
    private String address;
    private String phoneNumber;
    
}
