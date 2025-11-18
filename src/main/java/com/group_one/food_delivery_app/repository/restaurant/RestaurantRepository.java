package com.group_one.food_delivery_app.repository.restaurant;

import com.group_one.food_delivery_app.dto.RegisterShopDto;
import com.group_one.food_delivery_app.entity.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantModel,Long> {
    @Query(value = "SELECT CAST(CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END  AS Bit) IsExists FROM RESTAURANT WHERE  UPPER(NAME)=UPPER(:NAME)",nativeQuery = true)
    Boolean isExistsName(@Param("NAME") String name);
}
