package com.group_one.food_delivery_app.controller;

import com.group_one.food_delivery_app.dto.ClaimDto;
import com.group_one.food_delivery_app.dto.RegisterShopDto;
import com.group_one.food_delivery_app.infrastructure.IRestaurantService;
import com.group_one.food_delivery_app.security.JwtService;
import com.group_one.food_delivery_app.service.restaurant.RestaurantService;
import com.group_one.food_delivery_app.utils.ApiResponse.APIResponse;
import com.group_one.food_delivery_app.utils.mapper.GlobalMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "api/v1/restaurant")
public class RestaurantController {
    @Autowired
    private IRestaurantService restaurantService;

    @PostMapping("/register")
    public APIResponse<String> registerRestaurant (HttpServletRequest request, @RequestBody RegisterShopDto dto) {
        var claimDto = (ClaimDto) request.getAttribute("currentUser");
        var apiResponse = new APIResponse<String>();
        apiResponse.setTimestamp(LocalDateTime.now());
        try{

            var restaurantModel = GlobalMapper.fromRestaurantDtoToModel(dto);
            restaurantModel.setId(claimDto.getId());
            restaurantModel.setCreatedat(LocalDate.now());
            var isRegister = restaurantService.RegisterShop(restaurantModel);
            if (isRegister.containsKey("isSuccess")) {
                apiResponse.setResponseStatus(HttpStatus.OK);
                apiResponse.setMessage("Restaurant registered successfully");
                apiResponse.setData(isRegister.get("message").toString());
                apiResponse.setIsSuccess(Boolean.TRUE);
                apiResponse.setStatusCode(HttpStatus.OK.value());
            }else{
                apiResponse.setResponseStatus(HttpStatus.BAD_REQUEST);
                apiResponse.setMessage("Restaurant registered unsuccessfully");
                apiResponse.setData(isRegister.get("message").toString());
                apiResponse.setIsSuccess(Boolean.FALSE);
                apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            }
        }catch (Exception e) {
            apiResponse.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setMessage("Something went wrong");
            apiResponse.setData("Error : " + e.getMessage());
            apiResponse.setIsSuccess(Boolean.FALSE);
            apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return apiResponse;
    }
}
