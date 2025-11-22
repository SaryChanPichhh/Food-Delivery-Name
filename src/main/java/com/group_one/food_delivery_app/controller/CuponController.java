package com.group_one.food_delivery_app.controller;

import com.group_one.food_delivery_app.dto.ClaimDto;
import com.group_one.food_delivery_app.dto.CuponDto;
import com.group_one.food_delivery_app.entity.CuponModel;
import com.group_one.food_delivery_app.infrastructure.ICuponService;
import com.group_one.food_delivery_app.infrastructure.IRestaurantService;
import com.group_one.food_delivery_app.utils.ApiResponse.APIResponse;
import com.group_one.food_delivery_app.utils.mapper.GlobalMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping("api/v1/cupon")
public class CuponController {

    @Autowired
    private ICuponService cuponService;
    @Autowired
    private IRestaurantService restaurantService;
    // ------------------------------------------------------
    //  CREATE CUPON
    // ------------------------------------------------------
    @PostMapping("")
    public ResponseEntity<APIResponse<String>> createCupon(
            HttpServletRequest request,
            @RequestBody CuponDto dto
    ) {
        var apiResponse = new APIResponse<String>();
        apiResponse.setTimestamp(LocalDateTime.now());

        try {
            var claimDto = (ClaimDto) request.getAttribute("currentUser");

            var resInfo = restaurantService.GetAll().stream()
                    .filter(x->x.getUser().getUserId().equals(claimDto.getId())).toList().getFirst();
            var newId = cuponService.GenerateAutoId(
                    resInfo.getId()
            );
            var cuponModel = GlobalMapper.fromCuponDtoToModel(dto);
            cuponModel.setCuponId(newId);
            cuponModel.setCreatedBy(claimDto.getUserName());
            cuponModel.setCreatedAt(LocalDate.now());
            var saved = cuponService.AddAsync(cuponModel);

            if (saved == 1) {
                apiResponse.setResponseStatus(HttpStatus.OK);
                apiResponse.setMessage("Cupon created successfully");
                apiResponse.setData(newId);
                apiResponse.setIsSuccess(true);
                apiResponse.setStatusCode(HttpStatus.OK.value());
                return ResponseEntity.ok(apiResponse);
            } else {
                apiResponse.setResponseStatus(HttpStatus.BAD_REQUEST);
                apiResponse.setMessage("Cupon created unsuccessfully");
                apiResponse.setData(null);
                apiResponse.setIsSuccess(false);
                apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.badRequest().body(apiResponse);
            }

        } catch (Exception e) {
            apiResponse.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setMessage("Something went wrong");
            apiResponse.setData("Error : " + e.getMessage());
            apiResponse.setIsSuccess(false);
            apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.internalServerError().body(apiResponse);
        }
    }

    // ------------------------------------------------------
    //  UPDATE CUPON
    // ------------------------------------------------------
    @PutMapping("")
    public ResponseEntity<APIResponse<Boolean>> updateCupon(
            HttpServletRequest request,
            @RequestBody CuponDto dto
    ) {
        var apiResponse = new APIResponse<Boolean>();
        apiResponse.setTimestamp(LocalDateTime.now());

        try {
            var claimDto = (ClaimDto) request.getAttribute("currentUser");

            var resInfo = restaurantService.GetAll().stream()
                    .filter(x->x.getUser().getUserId().equals(claimDto.getId())).toList().getFirst();
            var newId = cuponService.GenerateAutoId(
                    resInfo.getId()
            );
            var cuponModel = GlobalMapper.fromCuponDtoToModel(dto);
            cuponModel.setCuponId(newId);
            cuponModel.setUpdatedBy(claimDto.getUserName());
            cuponModel.setUpdatedAt(LocalDate.now());
            var affected = cuponService.UpdateAsync(cuponModel);

            if (affected == 1) {
                apiResponse.setResponseStatus(HttpStatus.OK);
                apiResponse.setMessage("Cupon updated successfully");
                apiResponse.setData(true);
                apiResponse.setIsSuccess(true);
                apiResponse.setStatusCode(HttpStatus.OK.value());
                return ResponseEntity.ok(apiResponse);
            } else {
                apiResponse.setResponseStatus(HttpStatus.BAD_REQUEST);
                apiResponse.setMessage("Cupon update failed");
                apiResponse.setData(false);
                apiResponse.setIsSuccess(false);
                apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.badRequest().body(apiResponse);
            }

        } catch (Exception e) {
            apiResponse.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setMessage("Something went wrong");
            apiResponse.setData(false);
            apiResponse.setIsSuccess(false);
            apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.internalServerError().body(apiResponse);
        }
    }

    // ------------------------------------------------------
    //  DELETE CUPON
    // ------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Boolean>> deleteCupon(
            @PathVariable String id
    ) {
        var apiResponse = new APIResponse<Boolean>();
        apiResponse.setTimestamp(LocalDateTime.now());

        try {
            cuponService.DeleteAsync(id);
            apiResponse.setResponseStatus(HttpStatus.OK);
            apiResponse.setMessage("Cupon deleted successfully");
            apiResponse.setData(true);
            apiResponse.setIsSuccess(true);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            apiResponse.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setMessage("Error deleting cupon");
            apiResponse.setData(false);
            apiResponse.setIsSuccess(false);
            apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.internalServerError().body(apiResponse);
        }
    }

    // ------------------------------------------------------
    //  GET ALL CUPONS
    // ------------------------------------------------------
    @GetMapping("/{restaurantId}")
    public ResponseEntity<APIResponse<List<CuponModel>>> getAllCupons(@PathVariable Integer restaurantId) {
        var apiResponse = new APIResponse<List<CuponModel>>();
        apiResponse.setTimestamp(LocalDateTime.now());

        try {
            var data = cuponService.GetByRestaurantId(restaurantId);

            apiResponse.setResponseStatus(HttpStatus.OK);
            apiResponse.setMessage("Cupon list retrieved successfully");
            apiResponse.setData(data);
            apiResponse.setIsSuccess(true);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            apiResponse.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setMessage("Something went wrong");
            apiResponse.setData(null);
            apiResponse.setIsSuccess(false);
            apiResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.internalServerError().body(apiResponse);
        }
    }
}
