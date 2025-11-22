package com.group_one.food_delivery_app.repository.cupon;

import com.group_one.food_delivery_app.entity.CuponModel;
import com.group_one.food_delivery_app.entity.MenuModel;
import com.group_one.food_delivery_app.entity.RestaurantModel;
import com.group_one.food_delivery_app.utils.enums.CuponType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CuponRepository extends JpaRepository<CuponModel,String> {
    @Query(value = "SELECT CUPON_AUTO_GENERATE_ID(:RES_ID)",nativeQuery = true)
    String GenerateAutoId(@Param("RES_ID") Integer restaurantId);
    @Transactional
    @Modifying
    @Query("""
UPDATE Cupon c 
SET 
    c.cuponName = :cuponName,
    c.cuponDescription = :cuponDescription,
    c.cuponType = :cuponType,
    c.minSpend = :minSpend,
    c.discount = :discount,
    c.cuponCode = :cuponCode,
    c.startDate = :startDate,
    c.endDate = :endDate,
    c.status = :status,
     c.menu = :menu, 
    c.updatedAt = :updatedAt,
    c.updatedBy = :updatedBy
WHERE c.cuponId = :cuponId
""")
    int updateCupon(
            String cuponId,
            String cuponName,
            String cuponDescription,
            CuponType cuponType,
            BigDecimal minSpend,
            Double discount,
            String cuponCode,
            LocalDate startDate,
            LocalDate endDate,
            boolean status,
            @Param("menu") MenuModel menu,
            LocalDate updatedAt,
            String updatedBy
    );
    List<CuponModel> findByRestaurantId(Integer restaurantId);
}
