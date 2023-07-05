package com.fef.services;

import java.util.List;

import com.fef.model.AStarEquipment;

public interface IEquipmentServices 
{
    
//    public AStarEquipment  save(AStarEquipment equipment);
//
    public AStarEquipment  update(AStarEquipment equipment);
//
//    public boolean delete(int id);

    public List<AStarEquipment> getAll();

    public AStarEquipment getByID(Integer id);
    
    public List<AStarEquipment> getOemNameOrFabName(String oemName,String fabName);

}
