package com.fef.dto;

public class AStarEquipmentRequestDTO
{

    private Integer machineId;
    private String fabName;
    private String oemName;
    private String defaultOemUrl;
    private boolean legacyService;
    private boolean isActive;
    private String appName;
    
    
    public Integer getMachineId()
    {
        return machineId;
    }
    public void setMachineId(Integer machineId)
    {
        this.machineId = machineId;
    }
    public String getFabName()
    {
        return fabName;
    }
    public void setFabName(String fabName)
    {
        this.fabName = fabName;
    }
    public String getOemName()
    {
        return oemName;
    }
    public void setOemName(String oemName)
    {
        this.oemName = oemName;
    }
    public String getDefaultOemUrl()
    {
        return defaultOemUrl;
    }
    public void setDefaultOemUrl(String defaultOemUrl)
    {
        this.defaultOemUrl = defaultOemUrl;
    }
    public boolean isLegacyService()
    {
        return legacyService;
    }
    public void setLegacyService(boolean legacyService)
    {
        this.legacyService = legacyService;
    }
    public boolean isActive()
    {
        return isActive;
    }
    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }
    public String getAppName()
    {
        return appName;
    }
    public void setAppName(String appName)
    {
        this.appName = appName;
    }
    
    
    
    

}
