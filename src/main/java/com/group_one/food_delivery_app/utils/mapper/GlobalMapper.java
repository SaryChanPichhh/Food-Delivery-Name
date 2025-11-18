package com.group_one.food_delivery_app.utils.mapper;

import com.group_one.food_delivery_app.dto.RegisterShopDto;
import com.group_one.food_delivery_app.entity.RestaurantModel;

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

        return restaurantModel;
    }
}

