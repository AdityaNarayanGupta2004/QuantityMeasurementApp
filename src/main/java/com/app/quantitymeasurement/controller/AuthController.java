package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login/{username}")
    public String login(@PathVariable String username) {
        return jwtUtil.generateToken(username);
    }
}