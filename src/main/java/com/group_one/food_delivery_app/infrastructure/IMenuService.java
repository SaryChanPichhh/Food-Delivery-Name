package com.group_one.food_delivery_app.infrastructure;

import com.group_one.food_delivery_app.entity.MenuModel;
import org.springframework.stereotype.Component;

@Component
public interface IMenuService extends IBasedService<MenuModel>{
    String GenerateAutoId(int restaurantId);
}
