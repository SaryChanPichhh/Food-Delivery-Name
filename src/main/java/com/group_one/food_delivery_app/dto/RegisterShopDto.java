package com.group_one.food_delivery_app.dto;

import jakarta.annotation.Nullable;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
@Data
public class RegisterShopDto {
    @NotNull
    private String shopName;
    @Nullable
    private String shopDescription;
    @NotNull
    private String shopAddress;
    @NotNull
    private String shopPhone;
    @Nullable
    private String shopEmail;
    @Nullable
    private String latLng;
    @Nullable
    private String zone;
    @Nullable
    private String branch;
}
