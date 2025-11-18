package com.group_one.food_delivery_app.utils.ApiResponse;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Data
public class APIResponse<T> {
    private Boolean isSuccess;
    private String message;
    private T data;
    private int statusCode;
    private HttpStatus responseStatus ;
    private LocalDateTime timestamp;
}
