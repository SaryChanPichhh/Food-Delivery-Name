package com.group_one.food_delivery_app.repository;

import com.group_one.food_delivery_app.entity.MenuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuModel,String> {
    @Query(value = "SELECT MENU_GENERATE_AUTO_ID(:RESTAURANT_ID) ")
    Optional<String> GenerateAutoId(@Param("RESTAURANT_ID") int restaurantId);
}
