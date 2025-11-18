package com.group_one.food_delivery_app.controller;

import com.group_one.food_delivery_app.dto.RegisterDto;
import com.group_one.food_delivery_app.dto.UserDto;
import com.group_one.food_delivery_app.infrastructure.IAuthenticationService;
import com.group_one.food_delivery_app.service.AuthenticationService;
import com.group_one.food_delivery_app.utils.ApiResponse.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/login")
    public APIResponse<String> login(@RequestBody UserDto userDto) {
        var apiResponse =new APIResponse();
        apiResponse.setTimestamp(LocalDateTime.now());
        try {
            String token = authenticationService.LoginAsync(userDto);
            apiResponse.setData(token);
            apiResponse.setStatusCode(200);
            apiResponse.setMessage("success");
            apiResponse.setResponseStatus(HttpStatus.OK);
            apiResponse.setIsSuccess(true);
           return apiResponse;
        } catch (Exception e) {
            apiResponse.setData(e.getStackTrace());
            apiResponse.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setIsSuccess(false);
            apiResponse.setMessage("unsuccess");
            return apiResponse;
        }
    }
    @PostMapping("/register")
    public APIResponse<Boolean> register(@Validated @RequestBody RegisterDto userDto) {
        var apiResponse =new APIResponse();
        apiResponse.setTimestamp(LocalDateTime.now());
        try {
            var affectedRow = authenticationService.Register(userDto);
            if(affectedRow){
                apiResponse.setData(true);
                apiResponse.setStatusCode(200);
                apiResponse.setMessage("success");
                apiResponse.setResponseStatus(HttpStatus.OK);
                apiResponse.setIsSuccess(true);
                return apiResponse;
            }else{
                apiResponse.setData(false);
                apiResponse.setResponseStatus(HttpStatus.BAD_REQUEST);
                apiResponse.setIsSuccess(false);
                apiResponse.setMessage("unsuccess");
                return apiResponse;
            }
        } catch (Exception e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setIsSuccess(false);
            apiResponse.setMessage("unsuccess");
            return apiResponse;
        }
    }
}
