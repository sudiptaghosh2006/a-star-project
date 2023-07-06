package com.fef.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fef.dto.AStarEquipmentRequestDTO;
import com.fef.exception.DuplicateEquipmentFoundException;
import com.fef.exception.EquipmentNotFoundException;
import com.fef.model.AStarEquipment;
import com.fef.repositories.IEquipmentRepository;
import com.fef.services.IEquipmentServices;
import com.fef.services.IOEMURLServices;

@Service
@Transactional
public class EquipmentServices implements IEquipmentServices
{

    @Autowired
    private IEquipmentRepository equipmentRepository;

    @Autowired
    private IOEMURLServices urlServices;
    
    @Autowired   
   private ModelMapper modelMapper;

    @Override
    public List<AStarEquipment> getAll()
    {
	List<AStarEquipment> findAll = (List) equipmentRepository.findAll();
	if (findAll.size() > 0)
	{
	    findAll.forEach(equipment -> updateUrl(equipment));
	    return (List<AStarEquipment>) findAll;
	}
	else
	{
	    throw new EquipmentNotFoundException("No Equipment found in DB ");
	}
    }

    @Override
    public AStarEquipment getByID(Integer id)
    {
//	https://capsmc.service-now.com/smc?id=smc_equipment&oem_name=A_SCAN_0010002

	AStarEquipment equipment = null;
	Optional<AStarEquipment> findById = equipmentRepository.findById(id);
	if (findById.isPresent())
	{
	    equipment = findById.get();
	    equipment = updateUrl(equipment);
	    return equipment;
	}
	else
	{
	    throw new EquipmentNotFoundException("Equipment not found in DB for id :: " + id);
	}
    }

    @Override
    public List<AStarEquipment> getOemNameOrFabName(String oemNameOrFabName)
    {
	List<AStarEquipment> dataList = equipmentRepository
		.findByIsActiveTrueAndOemNameOrIsActiveTrueAndFabName(oemNameOrFabName,oemNameOrFabName);
	if (!dataList.isEmpty())
	{
//	   	    equipment = updateUrl(equipment);
	    dataList.stream().forEach(equipment->updateUrl(equipment));
	    return dataList;
	}
	else
	{
	    throw new EquipmentNotFoundException(
		    "Equipment not found in DB for Oem Name or Fab Name =  " + oemNameOrFabName);
	}
    }

    private AStarEquipment updateUrl(AStarEquipment equipment)
    {
	if (equipment.getDefaultOemUrl() == null)
	{
	    if (equipment.isLegacyService())
	    {
		equipment.setOemUrl(urlServices.getLegacyURL());
	    }
	    else
	    {
		StringBuffer bufferUrl = new StringBuffer(urlServices.getSemiConnextURL()).append("?id=")
			.append(equipment.getId()).append("&oem_name=").append(equipment.getOemName());
		equipment.setOemUrl(bufferUrl.toString());
	    }
	}
	else
	{
	    equipment.setOemUrl(equipment.getDefaultOemUrl());

	}
	return equipment;
    }

    @Override
    public AStarEquipment update(AStarEquipment equipmentUpdate)
    {
	
	AStarEquipment equipment = null;
	Optional<AStarEquipment> findById = equipmentRepository.findById(equipmentUpdate.getId());
	if (findById.isPresent())
	{
	    equipment = findById.get();
	    equipment.setDefaultOemUrl(equipmentUpdate.getDefaultOemUrl());
	    equipment.setActive(equipmentUpdate.isActive());
	    equipment.setAppName(equipmentUpdate.getAppName());
	    equipment.setFabName(equipmentUpdate.getFabName());
	    equipment.setLegacyService(equipmentUpdate.isLegacyService());
	    equipment.setMachineId(equipmentUpdate.getMachineId());
	    equipment.setOemName(equipmentUpdate.getOemName());
	    
	    
	    
	    AStarEquipment savedEquipment = equipmentRepository.save(equipment);
	    return savedEquipment;
	}
	else
	{
	    throw new EquipmentNotFoundException("Equipment not found in DB for id :: " + equipmentUpdate.getId());
	}
    }

    @Override
    public AStarEquipment save(AStarEquipmentRequestDTO equipment)
    {
	checkBeforeSave(equipment.getAppName());	
	AStarEquipment newEquipment = modelMapper.map(equipment, AStarEquipment.class);
	
	AStarEquipment savedEquipment = equipmentRepository.save(newEquipment);
	
	return savedEquipment;
    }
    
    private void checkBeforeSave(String appName)
    {
	List<AStarEquipment> activeEquipmentWithAppName = equipmentRepository.findByAppName(appName);
	if(activeEquipmentWithAppName.size()>0)
	{
	    throw new DuplicateEquipmentFoundException("Active equipment found in DB with same app name = "+appName );
	}
    }
    

}
