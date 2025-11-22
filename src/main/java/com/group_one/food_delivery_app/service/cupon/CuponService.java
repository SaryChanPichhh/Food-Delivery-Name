package com.group_one.food_delivery_app.service.cupon;

import com.group_one.food_delivery_app.entity.CuponModel;
import com.group_one.food_delivery_app.infrastructure.ICuponService;
import com.group_one.food_delivery_app.repository.cupon.CuponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class CuponService implements ICuponService {
    @Autowired
    private CuponRepository cuponRepository;

    @Override
    public String GenerateAutoId(Integer resId) {
        if(resId < 0 )return "";
        return cuponRepository.GenerateAutoId(resId);
    }

    @Override
    public Integer AddAsync(CuponModel cuponModel) {
        var affectedRow = cuponRepository.save(cuponModel);
        if(affectedRow != null){
            return 1;
        }
        return 0;
    }

    @Override
    public Integer UpdateAsync(CuponModel cuponModel) {

        int affected = cuponRepository.updateCupon(
                cuponModel.getCuponId(),
                cuponModel.getCuponName(),
                cuponModel.getCuponDescription(),
                cuponModel.getCuponType(),
                cuponModel.getMinSpend(),
                cuponModel.getDiscount(),
                cuponModel.getCuponCode(),
                cuponModel.getStartDate(),
                cuponModel.getEndDate(),
                cuponModel.isStatus(),
                cuponModel.getMenu(),
                LocalDate.now(),
                cuponModel.getUpdatedBy()
        );
        return Math.max(affected, 0);
    }

    @Override
    public Integer DeleteAsync(String cuponId) {
        cuponRepository.deleteById(cuponId);
        return 1;
    }

    @Override
    public List<CuponModel> GetAsync() {
        return cuponRepository.findAll();
    }

    @Override
    public List<CuponModel> GetByRestaurantId(Integer restaurantId) {
        return cuponRepository.findByRestaurantId(restaurantId);
    }
}
