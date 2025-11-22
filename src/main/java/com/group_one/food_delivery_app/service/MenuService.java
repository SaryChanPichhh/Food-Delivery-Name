package com.group_one.food_delivery_app.service;

import com.group_one.food_delivery_app.entity.MenuModel;
import com.group_one.food_delivery_app.infrastructure.IMenuService;
import com.group_one.food_delivery_app.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.List;

@Service
public class MenuService implements IMenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Override
    public Integer AddAsync(MenuModel model) {
        var affectedRow  = menuRepository.save(model);
        if (affectedRow.hashCode() > 0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Integer UpdateAsync(MenuModel model) {
        return 0;
    }

    @Override
    public Integer DeleteAsync(MenuModel model) {
        menuRepository
                .delete(model);
        return 1;
    }

    @Override
    public List<MenuModel> GetAllAsync() {
        return menuRepository.findAll();
    }

    @Override
    public List<MenuModel> GetAllByFilterAsync(Dictionary<String, Object> filter) {
        return List.of();
    }

    @Override
    public String GenerateAutoId(int restaurantId) {
        return "menuRepository.G";
    }
}
