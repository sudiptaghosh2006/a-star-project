package com.fef.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fef.common.dto.ApplicationResponseData;
import com.fef.common.dto.ServiceResponseStatus;
import com.fef.common.dto.ServiceResponseStatusConstant;
import com.fef.dto.AStarEquipmentRequestDTO;
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

    @GetMapping("/api/v1/equipment/{id}")
    @Operation(tags = "Equipment Service", description = "Gets the equipment data for ID (primary key) ", summary = "Gets the equipment data for ID (primary key) ")
    public ResponseEntity<ApplicationResponseData< AStarEquipment>> getEquipment(@PathVariable Integer id)
    {
	LOGGER.debug("Started operation to retrieve equipment for :: {} ",id);
	
	AStarEquipment equipment = equipmentServices.getByID(id);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData< AStarEquipment> responseData=new ApplicationResponseData<>();
	responseData.setResponseData(equipment);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("Finished operation to retrieve equipment for :: {} ",id);

	return new ResponseEntity<ApplicationResponseData<AStarEquipment>>(responseData, HttpStatus.OK);
    }
    
    
    @GetMapping("/api/v1/equipments/")
    @Operation(tags = "Equipment Service", description = "Gets Active equipment data based on oemName or  fabName ", summary = "Gets Active equipment data based on oemName or  fabName equipment finder")
    public ResponseEntity<ApplicationResponseData< List<AStarEquipment>>> getEquipments(@RequestParam String oemNameOrfabName)
    {
	LOGGER.debug("Started operation to retrieve equipment for :: {} ",oemNameOrfabName);
	
	List<AStarEquipment> equipment = equipmentServices.getOemNameOrFabName(oemNameOrfabName);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData< List<AStarEquipment>> responseData=new ApplicationResponseData<>();
	responseData.setResponseData(equipment);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("Finished operation to retrieve equipment for :: {} ",oemNameOrfabName);

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
    @GetMapping("/api/v1/equipments/all")
    @Operation(tags = "Equipment Service", description = "Gets all equipmentsin DB ", summary = "All equipment finder")
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
    
    @PutMapping("/api/v1/equipment/")
    @Operation(tags = "Equipment Service", description = "Updates equipment's default oem url and active status in the DB", summary = "Updates equipment's default oem url and active status in the DB")
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
    
    
    @PostMapping("/api/v1/equipment/")
    @Operation(tags = "Equipment Service", description = "New equipment created in the DB", summary = "New equipment created in the DB")
    public ResponseEntity<ApplicationResponseData< AStarEquipment>> createEquipment(@RequestBody AStarEquipmentRequestDTO equipment )
    {
	LOGGER.debug("Started creating new equipment");
	
	AStarEquipment updatedEquipment = equipmentServices.save(equipment);
	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	ApplicationResponseData<AStarEquipment>responseData=new ApplicationResponseData<>();
	responseData.setResponseData(updatedEquipment);
	responseData.setResponseStatus(responseStatus);
	
	LOGGER.debug("finished creating new equipment");

	return new ResponseEntity<ApplicationResponseData<AStarEquipment>>(responseData, HttpStatus.OK);
	

    }

}
