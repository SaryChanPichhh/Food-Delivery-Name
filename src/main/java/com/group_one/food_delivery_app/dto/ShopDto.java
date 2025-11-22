package com.group_one.food_delivery_app.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.validation.annotation.Validated;

@Data

public class ShopDto {
    @NotNull
    private String shopName;
    @NotNull
    private String password;
    @NotNull
    private String branch;
}
