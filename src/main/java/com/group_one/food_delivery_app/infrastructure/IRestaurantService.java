package com.group_one.food_delivery_app.infrastructure;

import com.group_one.food_delivery_app.dto.RegisterShopDto;
import com.group_one.food_delivery_app.entity.RestaurantModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface IRestaurantService {
    Map<String,Object> RegisterShop(RestaurantModel dto);
    Boolean isExistsName(String name);
    List<RestaurantModel> GetAll();
}
