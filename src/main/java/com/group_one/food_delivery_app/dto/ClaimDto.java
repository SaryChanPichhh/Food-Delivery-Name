package com.group_one.food_delivery_app.dto;

import com.group_one.food_delivery_app.utils.enums.UserType;
import lombok.Data;

@Data

public class ClaimDto {
    private Long id;
    private String userName;
    private UserType userType;
    private String zone;
    private String branchCode;
}
