package com.fef.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fef.model.FEFEquipment;

public interface IEquipmentRepository extends CrudRepository<FEFEquipment, Integer>

{
    public List<FEFEquipment> findByOemNameOrFabName(String oemName, String fabName);

    public List<FEFEquipment> findByIsActiveTrueAndOemNameOrIsActiveTrueAndFabName(String oemName, String fabName);
    
    public List<FEFEquipment> findByAppName(String appName);

}
