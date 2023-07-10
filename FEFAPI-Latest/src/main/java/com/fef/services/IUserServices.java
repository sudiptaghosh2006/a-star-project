package com.fef.services;

import java.util.List;

import com.fef.model.FEFApplicationUser;

public interface IUserServices
{
    
    public FEFApplicationUser getUser(String userName);
//    public AStarApplicationUser saveUser(String userName);
    
    public List<FEFApplicationUser> getAllUser();

}
