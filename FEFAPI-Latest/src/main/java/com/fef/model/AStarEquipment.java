package com.fef.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;



@Entity
@Table(name = "AStarEquipments")
public class AStarEquipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id; 
    
    @Column(nullable=false)
    private Integer machineId; 
    
    @Column(nullable = false) 
    private String fabName;
    
    @Column(nullable = false) 
    private String oemName;
    
    private String defaultOemUrl;
    
    @Transient
    private String oemUrl;
 
    private boolean legacyService;
    private boolean isActive;
    
    @Column(nullable = false) 
    private String appName;
   
    
    public Integer getMachineId()
    {
        return machineId;
    }

    public AStarEquipment setMachineId(Integer machineId)
    {
        this.machineId = machineId;
        return this;
    }

    public String getAppName()
    {
        return appName;
    }

    public AStarEquipment setAppName(String appName)
    {
        this.appName = appName;
        return this;
    }

    public Integer getId()
    {
	return machineId;
    }

    public AStarEquipment setId(Integer id)
    {
	this.machineId = id;
	return this;
    }

    public String getOemName()
    {
	return oemName;
    }

    public AStarEquipment setOemName(String oemName)
    {
	this.oemName = oemName;
	return this;
    }
  
    public boolean isLegacyService()
    {
	return legacyService;
    }

    public AStarEquipment setLegacyService(boolean legacyService)
    {
	this.legacyService = legacyService;
	return this;

    }

    public String getDefaultOemUrl()
    {
        return defaultOemUrl;
    }

    public AStarEquipment setDefaultOemUrl(String defaultOemUrl)
    {
        this.defaultOemUrl = defaultOemUrl;
        return this;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public AStarEquipment setActive(boolean isActive)
    {
        this.isActive = isActive;
        return this;
    }

    public String getFabName()
    {
        return fabName;
    }

    public AStarEquipment setFabName(String fabName)
    {
        this.fabName = fabName;
        return this;
    }
    
    public String getOemUrl()
    {
        return oemUrl;
    }

    public AStarEquipment setOemUrl(String oemUrl)
    {
        this.oemUrl = oemUrl;
        return this;
    }

}
