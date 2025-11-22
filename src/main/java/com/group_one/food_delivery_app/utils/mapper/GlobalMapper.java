package com.group_one.food_delivery_app.utils.mapper;

import com.group_one.food_delivery_app.dto.CuponDto;
import com.group_one.food_delivery_app.dto.RegisterShopDto;
import com.group_one.food_delivery_app.entity.CuponModel;
import com.group_one.food_delivery_app.entity.RestaurantModel;

import java.time.LocalDate;

public class GlobalMapper {
    public static RestaurantModel fromRestaurantDtoToModel( RegisterShopDto dto){
        var restaurantModel = new RestaurantModel();
        restaurantModel.setName(dto.getShopName());
        restaurantModel.setAddress(dto.getShopAddress());
        restaurantModel.setEmail(dto.getShopEmail());
        restaurantModel.setPhone(dto.getShopPhone());
        restaurantModel.setBranch(dto.getBranch());
        restaurantModel.setZone(dto.getZone());
        restaurantModel.setDescription(dto.getShopDescription());
        restaurantModel.setLatLng(dto.getLatLng());


        return restaurantModel;
    }
    public static CuponModel fromCuponDtoToModel(CuponDto dto) {
        CuponModel model = new CuponModel();

        model.setCuponName(dto.getCuponName());
        model.setCuponDescription(dto.getCuponDescription());
        model.setCuponType(dto.getCuponType());
        model.setMinSpend(dto.getMinSpend());
        model.setDiscount(dto.getDiscount());
        model.setCuponCode(dto.getCuponCode());
        model.setStartDate(dto.getStartDate());
        model.setEndDate(dto.getEndDate());
        model.setStatus(dto.isStatus());
//        model.setMenuId(dto.getMenuId());
        return model;
    }
}

