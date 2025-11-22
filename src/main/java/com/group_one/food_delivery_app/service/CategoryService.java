package com.group_one.food_delivery_app.service;

import com.group_one.food_delivery_app.entity.CategoryModel;
import com.group_one.food_delivery_app.infrastructure.ICategoryService;
import com.group_one.food_delivery_app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.List;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Integer AddAsync(CategoryModel model) {
        var affectedRow =  categoryRepository.saveAndFlush(model);
        if(affectedRow.hashCode() > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer UpdateAsync(CategoryModel model) {
        return 0;
    }

    @Override
    public Integer DeleteAsync(CategoryModel model) {
        return 0;
    }

    @Override
    public List<CategoryModel> GetAllAsync() {
        return List.of();
    }

    @Override
    public List<CategoryModel> GetAllByFilterAsync(Dictionary<String, Object> filter) {
        return List.of();
    }
}
