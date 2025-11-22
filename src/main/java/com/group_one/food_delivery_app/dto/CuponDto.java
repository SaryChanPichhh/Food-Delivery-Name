package com.group_one.food_delivery_app.dto;

import com.group_one.food_delivery_app.entity.RestaurantModel;
import com.group_one.food_delivery_app.utils.enums.CuponType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class CuponDto {
    private String cuponName; // Set
    private String cuponDescription; // Set
    private CuponType cuponType; // Set
    private BigDecimal minSpend; // Set
    private Double discount; // Set
    private String cuponCode; // Set
    private LocalDate startDate; // Set
    private LocalDate endDate; // Set
    private boolean status; // Auto
    private String menuId; // Set
}
