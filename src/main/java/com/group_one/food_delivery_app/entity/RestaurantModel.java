package com.group_one.food_delivery_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "Restaurant")
public class RestaurantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String address;
    private String phone;
    private String email;
    private double rating;
    private String zone;
    private String branch;
    private String latLng;
    private LocalDate createdat;
    private LocalDate updatedat;
    @ManyToOne
    @JoinColumn(name = "userid")
    private UserModel user;

}
