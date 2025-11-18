package com.group_one.food_delivery_app.service;

import com.group_one.food_delivery_app.dto.ClaimDto;
import com.group_one.food_delivery_app.dto.RegisterDto;
import com.group_one.food_delivery_app.dto.UserDto;
import com.group_one.food_delivery_app.entity.UserModel;
import com.group_one.food_delivery_app.infrastructure.IAuthenticationService;
import com.group_one.food_delivery_app.repository.AuthenticationRepository;
import com.group_one.food_delivery_app.security.JwtService;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public String LoginAsync(UserDto userDto) {
        UserModel user = authenticationRepository.findByUserNameIgnoreCase(userDto.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return "Password mismatch"; // or return some appropriate message
        }
        String token =  jwtService.generateToken(user);
        return token;
    }

    @Override
    public Boolean Register(RegisterDto userDto) {
        if (authenticationRepository.existsByUsernameIgnoreCase(userDto.getUserName()).orElse(false)) {
            return false;
        }
        var userModel = new  UserModel();
        userModel.setUserName(userDto.getUserName());
        userModel.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userModel.setUserType(userDto.getUserType());
        userModel.setEmail(userDto.getEmail());
        userModel.setPhone(userDto.getPhone());
        userModel.setCreatedAt(LocalDate.now());
        authenticationRepository.save(userModel);
        return true;
    }
}
