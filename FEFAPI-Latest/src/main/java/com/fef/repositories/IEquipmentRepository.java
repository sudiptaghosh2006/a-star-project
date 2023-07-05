package com.fef.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fef.model.AStarEquipment;

public interface IEquipmentRepository extends CrudRepository<AStarEquipment, Integer>

{
    public List<AStarEquipment> findByOemNameOrFabName(String oemName, String fabName);

    public List<AStarEquipment> findByIsActiveTrueAndOemNameOrIsActiveTrueAndFabName(String oemName, String fabName);

}
