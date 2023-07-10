package com.fef.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;



@Entity
@Table(name = "FefEquipments")
public class FEFEquipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id; 
    
    
//    machine_id- > equip-id  alpha-num
    @Column(nullable=false)
    private String equipmentId; 
    
//    Fab_name I* 
    @Column(nullable = true) 
    private String fabName;
   
    
//    FAB (EQUP NAME)
    @Column(nullable = true) 
    private String fabEquipmentName;
    
   
//    location L*
    @Column(nullable = true) 
    private String fabLocation;
    
//    TYPE (TWINSCAN)
    @Column(nullable = true) 
    private String equipmentType;
    
//    OEM EQP NAME 
    private String oemEquipmentName;
    
    @Column(nullable = true) 
    private String oemName;
    
   
    //rename default oem
    private String url;
    
   
    private boolean legacyService;
    private boolean isActive;
    
    @Column(nullable = false) 
    private String appName;
    
    
    @Transient
    private String displayOemUrl;
 
   
    public Integer getId()
    {
	return id;
    }

    public FEFEquipment setId(Integer id)
    {
	this.id = id;
	return this;
    }
    
   

    public String getAppName()
    {
        return appName;
    }

    public FEFEquipment setAppName(String appName)
    {
        this.appName = appName;
        return this;
    }

  

    public String getOemName()
    {
	return oemName;
    }

    public FEFEquipment setOemName(String oemName)
    {
	this.oemName = oemName;
	return this;
    }
  
    public boolean isLegacyService()
    {
	return legacyService;
    }

    public FEFEquipment setLegacyService(boolean legacyService)
    {
	this.legacyService = legacyService;
	return this;

    }

  
    public boolean isActive()
    {
        return isActive;
    }

    public FEFEquipment setActive(boolean isActive)
    {
        this.isActive = isActive;
        return this;
    }

    public String getFabName()
    {
        return fabName;
    }

    public FEFEquipment setFabName(String fabName)
    {
        this.fabName = fabName;
        return this;
    }

    public String getEquipmentId()
    {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public String getFabEquipmentName()
    {
        return fabEquipmentName;
    }

    public void setFabEquipmentName(String fabEquipmentName)
    {
        this.fabEquipmentName = fabEquipmentName;
    }

    public String getFabLocation()
    {
        return fabLocation;
    }

    public void setFabLocation(String fabLocation)
    {
        this.fabLocation = fabLocation;
    }

    public String getEquipmentType()
    {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType)
    {
        this.equipmentType = equipmentType;
    }

    public String getOemEquipmentName()
    {
        return oemEquipmentName;
    }

    public void setOemEquipmentName(String oemEquipmentName)
    {
        this.oemEquipmentName = oemEquipmentName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getDisplayOemUrl()
    {
        return displayOemUrl;
    }

    public void setDisplayOemUrl(String displayOemUrl)
    {
        this.displayOemUrl = displayOemUrl;
    }
    
   
}
