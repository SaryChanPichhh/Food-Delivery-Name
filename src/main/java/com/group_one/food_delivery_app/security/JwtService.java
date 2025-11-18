package com.group_one.food_delivery_app.security;

import com.group_one.food_delivery_app.dto.ClaimDto;
import com.group_one.food_delivery_app.entity.UserModel;
import com.group_one.food_delivery_app.utils.enums.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JwtService {
    private final String SECRET_KEY = "SETECGROUPONEDELIVERYAPPMIDTERMPROJECT";
    private final long EXPIRATION_MS = 86400000 * 365; // 1 day
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(UserModel user) {
        return Jwts.builder()
                .setSubject(user.getUserName())
                .claim("userId", user.getUserId())
                .claim("userType", user.getUserType().toString())
                .claim("zoneId", user.getZoneId())
                .claim("branchCode", user.getBranchCode())
                .claim("userName", user.getUserName())
                .claim("profile", user.getProfile())
                .claim("email", user.getEmail())
                .claim("phone", user.getPhone())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public ClaimDto decodeToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody();

        ClaimDto dto = new ClaimDto();
        dto.setId((Integer) claims.get("userId"));
        dto.setUserName((String) claims.get("userName"));
        dto.setUserType(Enum.valueOf(UserType.class, (String) claims.get("userType")));
        dto.setZone((String) claims.get("zoneId"));
        dto.setBranchCode((String) claims.get("branchCode"));
        return dto;
    }
}
