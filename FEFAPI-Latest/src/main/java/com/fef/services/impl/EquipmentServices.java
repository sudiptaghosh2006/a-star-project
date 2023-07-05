package com.fef.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<AStarEquipment> getOemNameOrFabName(String oemName, String fabName)
    {
//	https://capsmc.service-now.com/smc?id=smc_equipment&oem_name=A_SCAN_0010002

	List<AStarEquipment> dataList = equipmentRepository
		.findByIsActiveTrueAndOemNameOrIsActiveTrueAndFabName(oemName, fabName);
	if (!dataList.isEmpty())
	{
//	   	    equipment = updateUrl(equipment);
	    dataList.stream().forEach(equipment->updateUrl(equipment));
	    return dataList;
	}
	else
	{
	    throw new EquipmentNotFoundException(
		    "Equipment not found in DB for Oem Name =" + oemName + " or Fab Name =  " + fabName);
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
	    AStarEquipment savedEquipment = equipmentRepository.save(equipment);
	    return savedEquipment;
	}
	else
	{
	    throw new EquipmentNotFoundException("Equipment not found in DB for id :: " + equipmentUpdate.getId());
	}
    }

}
