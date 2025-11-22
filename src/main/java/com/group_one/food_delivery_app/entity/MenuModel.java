package com.group_one.food_delivery_app.entity;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity(name = "Menu")
public class MenuModel {
    @Id
    private String menuId;
    private String menuDesc;
    private String description;
    private String salePrice;
    private String imageUrl;
    private Boolean status;
    private String createdBy;
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryModel category;
}
