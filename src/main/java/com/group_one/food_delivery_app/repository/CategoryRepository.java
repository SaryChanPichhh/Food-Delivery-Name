package com.group_one.food_delivery_app.repository;

import com.group_one.food_delivery_app.entity.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel,String> {
}
