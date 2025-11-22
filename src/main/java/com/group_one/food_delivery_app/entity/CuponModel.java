package com.group_one.food_delivery_app.entity;

import com.group_one.food_delivery_app.utils.enums.CuponType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity(name = "Cupon")
public class CuponModel {
    @Id
    private String cuponId; // Auto
    private String cuponName; // Set
    private String cuponDescription; // Set
    private CuponType cuponType; // Set
    private BigDecimal minSpend; // Set
    private Double discount; // Set
    private String cuponCode; // Set
    private LocalDate startDate; // Set
    private LocalDate endDate; // Set
    private boolean status; // Auto
    private LocalDate createdAt ;
    private LocalDate updatedAt;
    private String createdBy;
    private String updatedBy;
    @ManyToOne
    @JoinColumn(name = "menuId")
    private MenuModel menu; // Set
}
