package com.fef.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fef.model.AStarEquipmentUrl;

//public interface IEquipmentUrlRepository extends CrudRepository<AStarEquipmentUrl, Integer>
public interface IEquipmentUrlRepository extends CrudRepository<AStarEquipmentUrl, String>
{

}
