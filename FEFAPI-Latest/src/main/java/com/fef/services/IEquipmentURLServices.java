package com.fef.services;



import java.util.List;

import com.fef.model.AStarEquipmentUrl;

public interface IEquipmentURLServices
{
    
    public AStarEquipmentUrl createEquipmentURL(AStarEquipmentUrl url);
    
    public AStarEquipmentUrl updateEquipmentURL(AStarEquipmentUrl url);
    
    public boolean deleteEquipmentURL(String systemId);
    
    public AStarEquipmentUrl getEquipmentURL(String systemId);
       
    public List<AStarEquipmentUrl> getAllEquipmentURL();
    
    
    
    
    
    
}
