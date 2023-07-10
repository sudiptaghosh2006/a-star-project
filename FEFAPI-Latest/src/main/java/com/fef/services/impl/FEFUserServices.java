package com.fef.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fef.common.dto.UserType;
import com.fef.exception.UserNotFoundException;
import com.fef.model.FEFApplicationUser;
import com.fef.repositories.IUserRepository;
import com.fef.services.IUserServices;

@Service
@Transactional
public class FEFUserServices implements IUserServices
{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public FEFApplicationUser getUser(String userName)
    {
	Optional<FEFApplicationUser> optional = userRepository.findById(userName);
	if (optional.isPresent())
	{
	    return optional.get();
	}
	else
	{
//	    throw new UserNotFoundException("No user found in DB ");

	    FEFApplicationUser applicationUser = generateUser(userName);
	    userRepository.save(applicationUser);
	    return applicationUser;
	}
    }

    private FEFApplicationUser generateUser(String userName)
    {

	return new FEFApplicationUser().setUserName(userName).setUserType(UserType.USER);
    }

    @Override
    public List<FEFApplicationUser> getAllUser()
    {
	 List<FEFApplicationUser> list = (List<FEFApplicationUser>) userRepository.findAll();
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
