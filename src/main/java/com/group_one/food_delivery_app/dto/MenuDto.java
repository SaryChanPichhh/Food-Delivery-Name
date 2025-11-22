package com.group_one.food_delivery_app.dto;

import com.group_one.food_delivery_app.entity.CategoryModel;
import com.group_one.food_delivery_app.entity.CuponModel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public class MenuDto
{
    private String menuDesc;
    private String description;
    private String salePrice;
    private String imageUrl;
    private Boolean status;
    private String categoryId;
}
