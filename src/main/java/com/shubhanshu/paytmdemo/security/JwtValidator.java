package com.shubhanshu.paytmdemo.security;

import com.shubhanshu.paytmdemo.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator{
    private static String secretKey = "One97";

    public JwtUser validate(String token) {
        JwtUser user = null;
        try{

            Claims body = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            if(body == null) {
                System.out.println("Here");
            }
            user = new JwtUser();
            user.setUsername((String) body.get("username"));

            user.setId(Long.parseLong((String) body.get("id")));
            user.setRole((String) body.get("role"));
        } catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }
}
