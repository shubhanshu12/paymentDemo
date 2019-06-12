package com.shubhanshu.paytmdemo.controller;

import com.shubhanshu.paytmdemo.model.JwtUser;
import com.shubhanshu.paytmdemo.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    JwtGenerator jwtGenerator;

    @PostMapping
    public String generate(@RequestBody final JwtUser user) {
        return jwtGenerator.generate(user);
    }
}
