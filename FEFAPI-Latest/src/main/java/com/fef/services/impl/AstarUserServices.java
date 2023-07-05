package com.fef.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fef.common.dto.UserType;
import com.fef.exception.UserNotFoundException;
import com.fef.model.AStarApplicationUser;
import com.fef.repositories.IUserRepository;
import com.fef.services.IUserServices;

@Service
@Transactional
public class AstarUserServices implements IUserServices
{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public AStarApplicationUser getUser(String userName)
    {
	Optional<AStarApplicationUser> optional = userRepository.findById(userName);
	if (optional.isPresent())
	{
	    return optional.get();
	}
	else
	{
//	    throw new UserNotFoundException("No user found in DB ");

	    AStarApplicationUser applicationUser = generateUser(userName);
	    userRepository.save(applicationUser);
	    return applicationUser;
	}
    }

    private AStarApplicationUser generateUser(String userName)
    {

	return new AStarApplicationUser().setUserName(userName).setUserType(UserType.USER);
    }

    @Override
    public List<AStarApplicationUser> getAllUser()
    {
	 List<AStarApplicationUser> list = (List<AStarApplicationUser>) userRepository.findAll();
	if (list.size()>0)
	{
	    return list;
	}
	else
	{
	    throw new UserNotFoundException("No user found in DB ");	 
	}

    }

}
