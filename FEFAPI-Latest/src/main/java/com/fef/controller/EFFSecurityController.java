package com.fef.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fef.jwt.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class EFFSecurityController
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EFFSecurityController.class);
    
    @ Autowired
    private JwtUtil jwtUtil;
    
    @GetMapping("/api/v1/security/token/{userName}")
    @Operation(tags = "User Security", description = "Generates Token for User ", summary = "User Token Generator")
    public String getGeneratedToken(@PathVariable String userName)
    {
	LOGGER.debug("Generate token for user ==> {}", userName);
	return  jwtUtil.generateToken(userName);
    }
    
    
    
}
