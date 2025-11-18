package com.group_one.food_delivery_app.dto;

import com.group_one.food_delivery_app.entity.UserModel;
import com.group_one.food_delivery_app.utils.enums.UserType;
import jakarta.annotation.Nullable;
import lombok.Data;
@Data
public class RegisterDto extends UserDto {
    private UserType userType;
    private String phone;
    @Nullable
    private String email;
}
