package com.fef.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fef.common.dto.ApplicationResponseData;
import com.fef.common.dto.ServiceResponseStatus;
import com.fef.common.dto.ServiceResponseStatusConstant;
import com.fef.model.FEFEquipmentUrl;
import com.fef.services.IEquipmentURLServices;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class FEFEquipmentUrlController
{    
    @Autowired
    private IEquipmentURLServices services;

    private static final Logger LOGGER = LoggerFactory.getLogger(FEFEquipmentController.class);

    @GetMapping("/api/v1/urls/")
    @Operation(tags = "Equipment URL Finder", description = "Gets all available Equipment URL ", summary = "Gets all available Equipment URL")
    public ResponseEntity<ApplicationResponseData< List<FEFEquipmentUrl>>> getAllActiveURL()
    {
	LOGGER.debug("Started operation to retrieve All Equipment URLs");
	
	List<FEFEquipmentUrl> allEquipmentURL = services.getAllEquipmentURL();
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData< List<FEFEquipmentUrl>> responseData=new ApplicationResponseData<>();
	responseData.setResponseData(allEquipmentURL);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("Finished operation to retrieve All Equipment URLs");

	return new ResponseEntity<ApplicationResponseData<List<FEFEquipmentUrl>>>(responseData, HttpStatus.OK);
	

    }
    
    @GetMapping("/api/v1/urls/{systemId}")
    @Operation(tags = "Equipment URL Finder", description = "Gets the Equipment URL ", summary = "Equipment URL finder")
    public ResponseEntity<ApplicationResponseData<FEFEquipmentUrl>> getAllActiveURLByID(@PathVariable String systemId )
    {
	LOGGER.debug("Started operation to retrieve Equipment URLs for :: {}",systemId);
	
	FEFEquipmentUrl url = services.getEquipmentURL(systemId);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData< FEFEquipmentUrl> responseData=new ApplicationResponseData<>();
	responseData.setResponseData(url);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("Finished operation to retrieve  Equipment URLs for :: ",systemId);

	return new ResponseEntity<ApplicationResponseData< FEFEquipmentUrl>>(responseData, HttpStatus.OK);
	

    }
    
    
    @PostMapping("/api/v1/urls/")
    @Operation(tags = "Equipment URL Finder", description = "Creates new Equipment URL ", summary = "Creates new Equipment URL")
    public ResponseEntity<ApplicationResponseData<FEFEquipmentUrl>> createActiveURL(@RequestBody FEFEquipmentUrl url )
    {
	LOGGER.debug("Started operation to create Equipment URLs for :: {}",url);
	
	FEFEquipmentUrl newURL = services.createEquipmentURL(url);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData< FEFEquipmentUrl> responseData=new ApplicationResponseData<>();
	responseData.setResponseData(newURL);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("Finished operation to create  Equipment URLs for :: ",url);

	return new ResponseEntity<ApplicationResponseData< FEFEquipmentUrl>>(responseData, HttpStatus.OK);
    }
    
    @PutMapping("/api/v1/urls/")
    @Operation(tags = "Equipment URL Finder", description = "Update the Equipment URL ", summary = "Update the Equipment URL ")
    public ResponseEntity<ApplicationResponseData<FEFEquipmentUrl>> updateActiveURL(@RequestBody FEFEquipmentUrl url )
    {
	LOGGER.debug("Started operation to update Equipment URLs for :: {}",url);
	
	FEFEquipmentUrl newURL = services.updateEquipmentURL(url);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData< FEFEquipmentUrl> responseData=new ApplicationResponseData<>();
	responseData.setResponseData(newURL);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("Finished operation to update Equipment URLs for :: ",url);

	return new ResponseEntity<ApplicationResponseData< FEFEquipmentUrl>>(responseData, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/urls/{id}")
    @Operation(tags = "Equipment URL Finder", description = "Delete the Equipment URL ", summary = "Delete the Equipment URL")
    public ResponseEntity<ApplicationResponseData<Boolean>> deleteActiveURL(@RequestBody String systemId )
    {
	LOGGER.debug("Started operation to delete Equipment URLs for :: {}",systemId);
	
	 boolean deleteEquipmentURL = services.deleteEquipmentURL(systemId);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData< Boolean> responseData=new ApplicationResponseData<>();
	responseData.setResponseData(deleteEquipmentURL);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("Finished operation to delete Equipment URLs for :: ",systemId);

	return new ResponseEntity<ApplicationResponseData< Boolean>>(responseData, HttpStatus.OK);
    }

    
}
