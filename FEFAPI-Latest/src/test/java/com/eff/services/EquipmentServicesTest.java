package com.eff.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fef.*;
import com.fef.exception.EquipmentNotFoundException;
import com.fef.model.AStarEquipment;
import com.fef.repositories.IEquipmentRepository;
import com.fef.services.IOEMURLServices;
import com.fef.services.impl.EquipmentServices;

@ExtendWith(MockitoExtension.class)
class EquipmentServicesTest
{

    @Mock
    private IEquipmentRepository equipmentRepository;

    @Mock
    private IOEMURLServices ioemurlServices;

    @InjectMocks
    private EquipmentServices equipmentServices;

//    @Test
    void testGetAll()
    {
	List<AStarEquipment> list = new ArrayList<AStarEquipment>();
	AStarEquipment equip1 = new AStarEquipment()
		.setId(1)//.setLegacyService(true)
//		.setOemName("demo")
//		.setActive(false)
//		.setAppName("App1")//.setDefaultOemUrl("A@B.C")
//		.setMachineId(1)
//		.setFabName("MyFab")
		;
	

//	AStarEquipment equip2 = new AStarEquipment().setId(2).setLegacyService(false)
//		.setOemName("A_SCAN_0010002");

	list.add(equip1);
//	list.add(equip2);

	when(equipmentRepository.findAll()).thenReturn(list);
	when(ioemurlServices.getLegacyURL()).thenReturn("https://brespp.asml.com");
	when(ioemurlServices.getSemiConnextURL()).thenReturn("https://capsmc.service-now.com/smc");

	// test
	List<AStarEquipment> equipmentList = equipmentServices.getAll();

	assertEquals(1, equipmentList.size());
//	AStarEquipment equipment = equipmentList.get(0);
//	assertEquals(1, equipment.getId());
//	assertEquals("MyFab", equipment.getFabName());
//	assertEquals(1, equipment.getMachineId());
//	assertEquals("App1", equipment.getAppName());
//	assertEquals("demo", equipment.getOemName());
	

    }
    
    @Test
    void testGetAllThrowsException()
    {
	when(equipmentRepository.findAll()).thenReturn(new ArrayList<AStarEquipment>());
	assertThrows(EquipmentNotFoundException.class, () -> equipmentServices.getAll());

    }

    /*
     * @Test void testGetByID() { String findByID = "brespp"; String oemName =
     * "demo"; String oemDesc = "my oem"; String oemUrl = "https://brespp.asml.com";
     * // String findByID = "brespp";
     * 
     * AStarEquipment equip1 = new AStarEquipment()
     * .setScreenId("brespp").setLegacyService(true)
     * .setOemName(oemName).setOemDescription(oemDesc);
     * 
     * Optional<AStarEquipment> optional = Optional.of(equip1);
     * 
     * when(equipmentRepository.findById(findByID)).thenReturn(optional);
     * when(ioemurlServices.getLegacyURL()).thenReturn(oemUrl);
     * 
     * 
     * // test AStarEquipment equipment = equipmentServices.getByID(findByID);
     * assertEquals(findByID, equipment.getId()); assertEquals(oemDesc,
     * equipment.getOemDescription()); assertEquals(oemUrl, equipment.getOemUrl());
     * 
     * }
     */    
    /*
     * @Test void testGetByIDException() { String findByIDForException =
     * "brespp1234"; AStarEquipment equip1 = new
     * AStarEquipment().setScreenId("brespp").setLegacyService(true).setOemName(
     * "demo"); assertThrows(EquipmentNotFoundException.class, () ->
     * equipmentServices.getByID(findByIDForException));
     * 
     * }
     */
}
