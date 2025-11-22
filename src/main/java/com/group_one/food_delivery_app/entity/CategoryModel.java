package com.group_one.food_delivery_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "category")
public class CategoryModel {
    @Id
    private String categoryId;
    private String categoryName;
    private String categoryDesc;
    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private RestaurantModel restaurant;
}
