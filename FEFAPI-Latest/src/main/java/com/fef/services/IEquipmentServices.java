package com.fef.services;

import java.util.List;

import com.fef.dto.FEFEquipmentRequestDTO;
import com.fef.model.FEFEquipment;

public interface IEquipmentServices 
{
    
    public FEFEquipment  save(FEFEquipmentRequestDTO equipment);
//
    public FEFEquipment  update(FEFEquipment equipment);
//
//    public boolean delete(int id);

    public List<FEFEquipment> getAll();

    public FEFEquipment getByID(Integer id);
    
//    public List<AStarEquipment> getOemNameOrFabName(String oemName,String fabName);
    public List<FEFEquipment> getOemNameOrFabName(String oemNameOrFabName);

}
