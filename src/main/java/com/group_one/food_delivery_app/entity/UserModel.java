package com.group_one.food_delivery_app.entity;

import com.group_one.food_delivery_app.utils.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "Users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "branchCode")
    private String branchCode;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "userType")
    private UserType userType;
    @Column(name = "profile")
    private String profile;
    @Column(name = "zoneId")
    private String zoneId;
    @Column(name = "createdAt")
    private LocalDate createdAt;
    @Column(name = "updatedAt")
    private LocalDate updatedAt;
}
