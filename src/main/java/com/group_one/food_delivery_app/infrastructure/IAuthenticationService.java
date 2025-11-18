package com.group_one.food_delivery_app.infrastructure;

import com.group_one.food_delivery_app.dto.ClaimDto;
import com.group_one.food_delivery_app.dto.RegisterDto;
import com.group_one.food_delivery_app.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public interface IAuthenticationService {
    String LoginAsync(UserDto userDto);
    Boolean Register(RegisterDto userDto);
//    Boolean RegisterShop(RegisterDto userDto);
}
