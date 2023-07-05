package com.fef.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

@Entity
@Table(name = "AStarEquipmentUrls")
public class AStarEquipmentUrl
{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id; 
//    
    @Id
    private String systemId;
    
    
    @Column(nullable = false) 
    private String systemUrl;
    
    
    private boolean active;
    
    
    public String getSystemId()
    {
        return systemId;
    }
    public AStarEquipmentUrl setId(String systemId)
    {
        this.systemId = systemId;
        return this;
    }
    public String getSystemUrl()
    {
        return systemUrl;
    }
    public AStarEquipmentUrl setSystemUrl(String systemUrl)
    {
        this.systemUrl = systemUrl;
        return this;
    }
    public boolean isActive()
    {
	return active;
    }
    public void setActive(boolean active)
    {
	this.active = active;
    }
    
    
    
}
