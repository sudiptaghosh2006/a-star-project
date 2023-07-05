package com.fef.services;

import java.util.List;

import com.fef.model.AStarApplicationUser;

public interface IUserServices
{
    
    public AStarApplicationUser getUser(String userName);
//    public AStarApplicationUser saveUser(String userName);
    
    public List<AStarApplicationUser> getAllUser();

}
