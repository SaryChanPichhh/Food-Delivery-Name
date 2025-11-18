package com.group_one.food_delivery_app.repository;

import com.group_one.food_delivery_app.dto.UserDto;
import com.group_one.food_delivery_app.entity.UserModel;
import com.group_one.food_delivery_app.utils.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<UserModel,Long> {
    @Query(value = "SELECT createdat createdAt,updatedat updatedAt,userId,branchcode branchCode,email,password,phone,profile,username userName,usertype userType,zoneid zoneId FROM Users u WHERE LOWER(u.userName) = LOWER(:userName)",nativeQuery = true)
    Optional<UserModel> findByUserNameIgnoreCase(@Param("userName") String userName);
    @Query(value = "SELECT CAST(CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS BIT ) " +
            "IsExists FROM USERS WHERE UPPER(USERNAME) = :USERNAME", nativeQuery = true)
    Optional<Boolean> existsByUsernameIgnoreCase(@Param("USERNAME") String userName);

}
