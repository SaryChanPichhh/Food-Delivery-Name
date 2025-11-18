package com.group_one.food_delivery_app.dto;

import com.group_one.food_delivery_app.utils.enums.UserType;
import lombok.Data;

@Data
public class UserDto {
    private String userName;
    private String password;
}
