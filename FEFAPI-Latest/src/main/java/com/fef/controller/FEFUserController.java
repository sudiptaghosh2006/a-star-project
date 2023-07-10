package com.fef.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fef.common.dto.ApplicationResponseData;
import com.fef.common.dto.ServiceResponseStatus;
import com.fef.common.dto.ServiceResponseStatusConstant;
import com.fef.model.FEFApplicationUser;
import com.fef.services.IUserServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class FEFUserController
{
    @Autowired
    private IUserServices userServices;

    private static final Logger LOGGER = LoggerFactory.getLogger(FEFEquipmentController.class);

    @GetMapping("/api/v1/users/{userName}")
    @Operation(tags = "User Finder", description = "Gets the user data ", summary = "user finder")
    public ResponseEntity<ApplicationResponseData<FEFApplicationUser>> getUser(@PathVariable String userName)
    {
	LOGGER.debug("Started operation to retrieve User for :: {} ", userName);

	FEFApplicationUser user = userServices.getUser(userName);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData<FEFApplicationUser> responseData = new ApplicationResponseData<>();
	responseData.setResponseData(user);
	responseData.setResponseStatus(responseStatus);

	LOGGER.debug("Finished operation to retrieve equipment for :: {} ", userName);

	return new ResponseEntity<ApplicationResponseData<FEFApplicationUser>>(responseData, HttpStatus.OK);

    }

    @GetMapping("/api/v1/users/")
    @Operation(tags = "User Finder", description = "Gets entire user list in the system.This is for ADMIN user ", summary = "user finder")
    public ResponseEntity<ApplicationResponseData<List<FEFApplicationUser>>> getAllUsers()
    {
	LOGGER.debug("Started operation to retrieve all users for ");

	List<FEFApplicationUser> allUser = userServices.getAllUser();
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData<List<FEFApplicationUser>> responseData = new ApplicationResponseData<>();
	responseData.setResponseData(allUser);
	responseData.setResponseStatus(responseStatus);

	LOGGER.debug("Finished operation to retrieve all users");

	return new ResponseEntity<ApplicationResponseData<List<FEFApplicationUser>>>(responseData, HttpStatus.OK);

    }
}
