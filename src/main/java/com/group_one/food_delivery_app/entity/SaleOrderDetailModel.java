package com.group_one.food_delivery_app.entity;

import com.group_one.food_delivery_app.utils.enums.OrderType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

public class SaleOrderDetailModel {
    private String transRef;
    private String orderLine; // TOTAL ITEM PER AMOUNT
    private OrderType orderType;
    private Integer quantity;
    private Double discount;
    private Double salePrice;
    private Double totalPrice;
    @OneToMany
    @JoinColumn(name = "menuId")
    private MenuModel menu;
}
