package com.fef.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fef.common.dto.ApplicationResponseData;
import com.fef.common.dto.ServiceResponseStatus;
import com.fef.common.dto.ServiceResponseStatusConstant;
import com.fef.model.AStarEquipment;
import com.fef.services.IEquipmentServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
//@RequestMapping()
public class FEFEquipmentController
{
    @Autowired
    private IEquipmentServices equipmentServices;

    private static final Logger LOGGER = LoggerFactory.getLogger(FEFEquipmentController.class);

    @GetMapping("/api/v1/equipments/{oemID}")
    @Operation(tags = "Equipment Finder", description = "Gets the equipment data ", summary = "equipment finder")
    public ResponseEntity<ApplicationResponseData< AStarEquipment>> getEquipment(@PathVariable Integer oemID)
    {
	LOGGER.debug("Started operation to retrieve equipment for :: {} ",oemID);
	
	AStarEquipment equipment = equipmentServices.getByID(oemID);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData< AStarEquipment> responseData=new ApplicationResponseData<>();
	responseData.setResponseData(equipment);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("Finished operation to retrieve equipment for :: {} ",oemID);

	return new ResponseEntity<ApplicationResponseData<AStarEquipment>>(responseData, HttpStatus.OK);
	

    }
    
    
    @GetMapping("/api/v1/equipments/{oemName}/{fabName}")
    @Operation(tags = "Equipment Finder", description = "Gets Active equipment data based on oemName or  fabName ", summary = "Gets Active equipment data based on oemName or  fabName equipment finder")
    public ResponseEntity<ApplicationResponseData< List<AStarEquipment>>> getEquipment(@PathVariable String oemName,@PathVariable String fabName)
    {
//	LOGGER.debug("Started operation to retrieve equipment for :: {} ",oemID);
	
	List<AStarEquipment> equipment = equipmentServices.getOemNameOrFabName(oemName,fabName);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData< List<AStarEquipment>> responseData=new ApplicationResponseData<>();
	responseData.setResponseData(equipment);
	responseData.setResponseStatus(responseStatus);
	
//	LOGGER.debug("Finished operation to retrieve equipment for :: {} ",oemID);

	return new ResponseEntity<ApplicationResponseData<List<AStarEquipment>>>(responseData, HttpStatus.OK);
    }
    
    
    /*
     * @GetMapping("/users/api/v1/equipments/{oemNameOrfabName}")
     * 
     * @Operation(tags = "Equipment Finder", description =
     * "Gets Active equipment data based on oemName or  fabName", summary =
     * "Gets Active equipment data based on oemName or  fabName ") public
     * ResponseEntity<ApplicationResponseData< List<AStarEquipment>>>
     * getEquipmentByFabOrOem(@PathVariable String oemNameOrfabName) { //
     * LOGGER.debug("Started operation to retrieve equipment for :: {} ",oemID);
     * 
     * List<AStarEquipment> equipment =
     * equipmentServices.getOemNameOrFabName(oemNameOrfabName,oemNameOrfabName);
     * ServiceResponseStatus responseStatus = new ServiceResponseStatus();
     * responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
     * responseStatus.setResponseMessage(ServiceResponseStatusConstant.
     * SUCCESS_MESSAGE); ApplicationResponseData< List<AStarEquipment>>
     * responseData=new ApplicationResponseData<>();
     * responseData.setResponseData(equipment);
     * responseData.setResponseStatus(responseStatus);
     * 
     * // LOGGER.debug("Finished operation to retrieve equipment for :: {} ",oemID);
     * 
     * return new
     * ResponseEntity<ApplicationResponseData<List<AStarEquipment>>>(responseData,
     * HttpStatus.OK); }
     */    
    @GetMapping("/api/v1/equipments/")
    @Operation(tags = "Equipment Finder", description = "Gets all equipmentsin DB ", summary = "All equipment finder")
    public ResponseEntity<ApplicationResponseData< List< AStarEquipment>>> getAllEquipment()
    {
	LOGGER.debug("Started looking for All equipments");
	
	 List<AStarEquipment> list = equipmentServices.getAll();
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData<List<AStarEquipment> >responseData=new ApplicationResponseData<>();
	responseData.setResponseData(list);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("Finished operation to retrieve All equipments");

	return new ResponseEntity<ApplicationResponseData< List< AStarEquipment>>>(responseData, HttpStatus.OK);
	

    }
    
    @PutMapping("/api/v1/equipments/")
    @Operation(tags = "Equipment Finder", description = "Updates equipment's default oem url and active status in the DB", summary = "Updates equipment's default oem url and active status in the DB")
    public ResponseEntity<ApplicationResponseData< AStarEquipment>> updateEquipment(@RequestBody AStarEquipment equipment )
    {
	LOGGER.debug("Started looking for All equipments");
	
	 AStarEquipment updatedEquipment = equipmentServices.update(equipment);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData<AStarEquipment>responseData=new ApplicationResponseData<>();
	responseData.setResponseData(updatedEquipment);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("Finished operation to retrieve All equipments");

	return new ResponseEntity<ApplicationResponseData<AStarEquipment>>(responseData, HttpStatus.OK);
	

    }

}
