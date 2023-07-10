package com.fef.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fef.model.FEFApplicationUser;

public interface IUserRepository extends CrudRepository<FEFApplicationUser, String>
{

}
