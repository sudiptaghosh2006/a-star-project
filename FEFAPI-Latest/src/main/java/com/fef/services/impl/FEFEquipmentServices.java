package com.fef.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fef.dto.FEFEquipmentRequestDTO;
import com.fef.exception.DuplicateEquipmentFoundException;
import com.fef.exception.EquipmentNotFoundException;
import com.fef.model.FEFEquipment;
import com.fef.repositories.IEquipmentRepository;
import com.fef.services.IEquipmentServices;
import com.fef.services.IOEMURLServices;

@Service
@Transactional
public class FEFEquipmentServices implements IEquipmentServices
{

    @Autowired
    private IEquipmentRepository equipmentRepository;

    @Autowired
    private IOEMURLServices urlServices;
    
    @Autowired   
   private ModelMapper modelMapper;

    @Override
    public List<FEFEquipment> getAll()
    {
	List<FEFEquipment> findAll = (List) equipmentRepository.findAll();
	if (findAll.size() > 0)
	{
	    findAll.forEach(equipment -> updateUrl(equipment));
	    return (List<FEFEquipment>) findAll;
	}
	else
	{
	    throw new EquipmentNotFoundException("No Equipment found in DB ");
	}
    }

    @Override
    public FEFEquipment getByID(Integer id)
    {
//	https://capsmc.service-now.com/smc?id=smc_equipment&oem_name=A_SCAN_0010002

	FEFEquipment equipment = null;
	Optional<FEFEquipment> findById = equipmentRepository.findById(id);
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
    public List<FEFEquipment> getOemNameOrFabName(String oemNameOrFabName)
    {
	List<FEFEquipment> dataList = equipmentRepository
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

    private FEFEquipment updateUrl(FEFEquipment equipment)
    {
	if (equipment.getUrl() == null)
	{
	    if (equipment.isLegacyService())
	    {
		equipment.setDisplayOemUrl(urlServices.getLegacyURL());
	    }
	    else
	    {
		StringBuffer bufferUrl = new StringBuffer(urlServices.getSemiConnextURL()).append("?id=")
			.append(equipment.getId()).append("&oem_name=").append(equipment.getOemName());
		equipment.setDisplayOemUrl(bufferUrl.toString());
	    }
	}
	else
	{
	    equipment.setUrl(equipment.getUrl());

	}
	return equipment;
    }

    @Override
    public FEFEquipment update(FEFEquipment equipmentUpdate)
    {
	
	FEFEquipment equipment = null;
	Optional<FEFEquipment> findById = equipmentRepository.findById(equipmentUpdate.getId());
	if (findById.isPresent())
	{
	    equipment = findById.get();
	    equipment.setUrl(equipmentUpdate.getUrl());
	    equipment.setActive(equipmentUpdate.isActive());
	    equipment.setAppName(equipmentUpdate.getAppName());
	    equipment.setFabName(equipmentUpdate.getFabName());
	    equipment.setLegacyService(equipmentUpdate.isLegacyService());
	    equipment.setEquipmentId(equipmentUpdate.getEquipmentId());
	    equipment.setOemName(equipmentUpdate.getOemName());
	    
	    
	    
	    FEFEquipment savedEquipment = equipmentRepository.save(equipment);
	    return savedEquipment;
	}
	else
	{
	    throw new EquipmentNotFoundException("Equipment not found in DB for id :: " + equipmentUpdate.getId());
	}
    }

    @Override
    public FEFEquipment save(FEFEquipmentRequestDTO equipment)
    {
	checkBeforeSave(equipment.getAppName());	
	FEFEquipment newEquipment = modelMapper.map(equipment, FEFEquipment.class);
	
	FEFEquipment savedEquipment = equipmentRepository.save(newEquipment);
	
	return savedEquipment;
    }
    
    private void checkBeforeSave(String appName)
    {
	List<FEFEquipment> activeEquipmentWithAppName = equipmentRepository.findByAppName(appName);
	if(activeEquipmentWithAppName.size()>0)
	{
	    throw new DuplicateEquipmentFoundException("Active equipment found in DB with same app name = "+appName );
	}
    }
    

}
