package com.company.reports.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class UserInfo {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
 
    @Column(name = "name")
    private String name;
 
    @Column(name = "surname")
    private String surname;
 
    @Column(name = "age")
    private Integer age;
 
    @Column(name = "address")
    private String address;
 
    @Column(name = "phone_number")
    private String phoneNumber;
 
    @Column(name = "active")
    private boolean active;
 
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
 
    @Column(name = "created_at")
    private LocalDateTime createdAt;
 
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
 
}
