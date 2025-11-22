package com.group_one.food_delivery_app.controller;

import com.group_one.food_delivery_app.dto.ClaimDto;
import com.group_one.food_delivery_app.dto.RegisterShopDto;
import com.group_one.food_delivery_app.entity.UserModel;
import com.group_one.food_delivery_app.infrastructure.IAuthenticationService;
import com.group_one.food_delivery_app.infrastructure.IRestaurantService;
import com.group_one.food_delivery_app.repository.AuthenticationRepository;
import com.group_one.food_delivery_app.security.JwtService;
import com.group_one.food_delivery_app.service.restaurant.RestaurantService;
import com.group_one.food_delivery_app.utils.ApiResponse.APIResponse;
import com.group_one.food_delivery_app.utils.mapper.GlobalMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.oauth2.login.OAuth2LoginSecurityMarker;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping( "api/v1/restaurant")
public class RestaurantController {
    @Autowired
    private IRestaurantService restaurantService;
    @Autowired
    private AuthenticationRepository authenticationRepository;
    @PostMapping("/register")
    public ResponseEntity<APIResponse<String>> registerRestaurant (HttpServletRequest request, @RequestBody RegisterShopDto dto) {
        var claimDto = (ClaimDto) request.getAttribute("currentUser");
        var apiResponse = new APIResponse<String>();
        apiResponse.setTimestamp(LocalDateTime.now());
        try{
            var restaurantModel = GlobalMapper.fromRestaurantDtoToModel(dto);
            var userModel = authenticationRepository.findById(claimDto.getId()).orElse(new  UserModel());
            restaurantModel.setUser(userModel);
            restaurantModel.setCreatedAt(LocalDate.now());
            var isRegister = restaurantService.RegisterShop(restaurantModel);
            if ((Boolean)isRegister.get("isSuccess")) {
                apiResponse.setResponseStatus(HttpStatus.OK);
                apiResponse.setMessage("Restaurant registered successfully");
                apiResponse.setData(isRegister.get("message").toString());
                apiResponse.setIsSuccess(Boolean.TRUE);
                apiResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(apiResponse,HttpStatus.OK);
            }else{
                apiResponse.setResponseStatus(HttpStatus.BAD_REQUEST);
                apiResponse.setMessage("Restaurant registered unsuccessfully");
                apiResponse.setData(isRegister.get("message").toString());
                apiResponse.setIsSuccess(Boolean.FALSE);
                apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            apiResponse.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setMessage("Something went wrong");
            apiResponse.setData("Error : " + e.getMessage());
            apiResponse.setIsSuccess(Boolean.FALSE);
            apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(path = "/isexists/{name}")
    public ResponseEntity<APIResponse<Boolean>> isExists (@PathVariable String name){
        var apiResponse = new APIResponse<Boolean>();
        apiResponse.setTimestamp(LocalDateTime.now());
        try{
            var isRegister = restaurantService.isExistsName(name);
            if (isRegister) {
                apiResponse.setResponseStatus(HttpStatus.OK);
                apiResponse.setMessage("Restaurant is Exists");
                apiResponse.setData(true);
                apiResponse.setIsSuccess(Boolean.TRUE);
                apiResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(apiResponse,HttpStatus.OK);
            }else{
                apiResponse.setResponseStatus(HttpStatus.BAD_REQUEST);
                apiResponse.setMessage("Restaurant have not registered yet!");
                apiResponse.setData(false);
                apiResponse.setIsSuccess(Boolean.FALSE);
                apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            apiResponse.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setMessage("Something went wrong");
            apiResponse.setData(false);
            apiResponse.setIsSuccess(Boolean.FALSE);
            apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
