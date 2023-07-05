package com.eff.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fef.common.dto.ApplicationResponseData;
import com.fef.controller.FEFEquipmentController;
import com.fef.model.AStarEquipment;
import com.fef.services.IEquipmentServices;

@ExtendWith(MockitoExtension.class)
class EFFClientControllerTest
{

    @InjectMocks
    private FEFEquipmentController clientController;
    
    @Mock
    private IEquipmentServices equipmentServices;

    @Test
    void testGetAllEquipment()
    {
	List<AStarEquipment> list = new ArrayList<AStarEquipment>();
	AStarEquipment equip1 = new AStarEquipment().setId(1).setLegacyService(true).setOemName("demo");

	AStarEquipment equip2 = new AStarEquipment().setId(2).setLegacyService(false)
		.setOemName("A_SCAN_0010002");

	list.add(equip1);
	list.add(equip2);


        when(equipmentServices.getAll()).thenReturn(list);

        ResponseEntity<ApplicationResponseData<List<AStarEquipment>>> allEquipment = clientController.getAllEquipment();

        assertThat(allEquipment.getBody().getResponseData().size()).isEqualTo(2);
//        assertThat(result.getEmployeeList().get(0).getFirstName()).isEqualTo(employee1.getFirstName());
//        assertThat(result.getEmployeeList().get(1).getFirstName()).isEqualTo(employee2.getFirstName());
    }
    
    /*
     * @Test void testGetEquipmentById() { List<AStarEquipment> list = new
     * ArrayList<AStarEquipment>(); AStarEquipment equip1 = new
     * AStarEquipment().setScreenId("brespp").setLegacyService(true).setOemName(
     * "demo"); AStarEquipment equip2 = new
     * AStarEquipment().setScreenId("smc_equipment").setLegacyService(false)
     * .setOemName("A_SCAN_0010002"); list.add(equip1); list.add(equip2);
     * when(equipmentServices.getByID("brespp")).thenReturn(equip1);
     * 
     * ResponseEntity<ApplicationResponseData<AStarEquipment>> equipment =
     * clientController.getEquipment("brespp");
     * assertThat(equipment.getBody().getResponseData().getId()).isEqualTo("brespp")
     * ; assertThat(equipment.getBody().getResponseStatus().getResponseCode()).
     * isEqualTo(0);
     * assertThat(equipment.getBody().getResponseStatus().getDetailMessageList()).
     * isEqualTo(null); }
     */

}
