package com.uwilaminango.employeemanagmentsystem.secutity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTUtilizer {

    private final String SECRET_KEY_STRING = "ad ef 95 1b e5 17 2c 3a eb 6e 2b e4 fd 26 e7 ca 2f 24 33 f6 8e b3 3e fe b9 dc 89 7e 7d 07 d7 13 41 39 91 02 a0 8c 66 47 36 e5 3d 5b 34 d5 0b 85 67 21 a4 7c bb ec 50 e4 55 f0 be 1b 4c 11 0d 93";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    public String generateJWTToken(String username, String role){
        Map<String, Object> mp = new HashMap<>();//we created a map to hold custom data that we want to include in the jwt
        mp.put("username", username); //Username
        mp.put("role", role); //User Role(Admin/Manager/Employee)
        return Jwts.builder()
                .claims(mp)// Attaches your custom payload(username and role)
                .subject(username)// A general field for identifying the token (it can be used for username)
                .issuedAt(new Date())//when the token was created
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))//when the token is going to expire (2 hours)
                .signWith(key)//Secures the token with our secrete key
                .compact();//It is a final step to return the token as a string
    }

    public Map<String, String> validateToken(String token){
        Map<String, String> res = new HashMap<>();
        try{
            Claims claims = Jwts.parser()//used to configure the parser
                    .verifyWith(key)//Same key which was used for generation (Secret key that is used to verify)
                    .build()
                    .parseSignedClaims(token)// It verifies and parses the token
                    .getPayload();//returns the actual data inside the token

            res.put("username", claims.get("username",String.class));
            res.put("role",claims.get("role", String.class));
            res.put("code", "200");
        }catch(Exception e){
            res.put("code", "404");
            res.put("error", "Token expired. Please login again.");
            //res.put("error", e.getMessage());
        }
        return res;
    }

}
