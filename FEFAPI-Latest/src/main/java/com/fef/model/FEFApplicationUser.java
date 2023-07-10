package com.fef.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

@Entity
@Table(name = "FefApplicationUsers")
public class FEFApplicationUser
{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Id
    @Column(length = 50)
    private String userName;
    @Column(length = 15)
    private String userType;

    public String getUserType()
    {
	return userType;
    }

    public FEFApplicationUser setUserType(String userType)
    {
	this.userType = userType;
	return this;
    }

    public String getUserName()
    {
	return userName;
    }

    public FEFApplicationUser setUserName(String userName)
    {
	this.userName = userName;
	return this;
    }

}
