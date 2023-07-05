package com.fef.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fef.model.AStarApplicationUser;

public interface IUserRepository extends CrudRepository<AStarApplicationUser, String>
{

}
