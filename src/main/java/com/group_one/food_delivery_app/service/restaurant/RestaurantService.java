package com.group_one.food_delivery_app.service.restaurant;

import com.group_one.food_delivery_app.dto.RegisterShopDto;
import com.group_one.food_delivery_app.entity.RestaurantModel;
import com.group_one.food_delivery_app.infrastructure.IRestaurantService;
import com.group_one.food_delivery_app.repository.restaurant.RestaurantRepository;
import com.group_one.food_delivery_app.utils.mapper.GlobalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantService implements IRestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Override
    public Map<String,Object> RegisterShop(@Validated RestaurantModel dto) {
        Map<String,Object> response = new HashMap<>();
        if(restaurantRepository.isExistsName(dto.getName())) {
            response.put("message", "Restaurant name is valid");
            response.put("isSuccess", false);
            return response;
        }

        var affectedRow = restaurantRepository.save(dto);
        if(affectedRow != null){
            response.put("message","Restaurant has been registered successfully");
            response.put("isSuccess",true);
            return response;
        }else {
            response.put("message", "Restaurant has been registered unsuccessfully");
            response.put("isSuccess", false);
            return response;
            }
    }
    @Override
    public Boolean isExistsName(String name) {
        return restaurantRepository.isExistsName(name);
    }

    @Override
    public List<RestaurantModel> GetAll() {
        return restaurantRepository.findAll();
    }
}
