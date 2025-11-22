package com.group_one.food_delivery_app.infrastructure;

import java.util.Dictionary;
import java.util.List;

public interface IBasedService <T>{
    Integer AddAsync(T model);
    Integer UpdateAsync(T model);
    Integer DeleteAsync(T model);
    List<T> GetAllAsync();
    List<T> GetAllByFilterAsync(Dictionary<String, Object> filter);

}
