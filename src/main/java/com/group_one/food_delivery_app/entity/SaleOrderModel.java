package com.group_one.food_delivery_app.entity;

import com.group_one.food_delivery_app.utils.enums.SaleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleOrderModel {
    private String transRef;
    @Enumerated(EnumType.STRING)
    private SaleType saleType;
    private LocalDate transDate;
    private BigDecimal amount;
    private String saleRep;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

}
