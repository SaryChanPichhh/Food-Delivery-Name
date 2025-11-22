package com.group_one.food_delivery_app.infrastructure;

import com.group_one.food_delivery_app.entity.CuponModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ICuponService {
   String GenerateAutoId(Integer resId);
   Integer AddAsync(CuponModel cuponModel);
   Integer UpdateAsync(CuponModel cuponModel);
   Integer DeleteAsync(String cuponId);
   List<CuponModel> GetAsync();
   List<CuponModel> GetByRestaurantId(Integer restaurantId);
}
