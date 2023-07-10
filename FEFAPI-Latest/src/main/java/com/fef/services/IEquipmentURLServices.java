package com.fef.services;



import java.util.List;

import com.fef.model.FEFEquipmentUrl;

public interface IEquipmentURLServices
{
    
    public FEFEquipmentUrl createEquipmentURL(FEFEquipmentUrl url);
    
    public FEFEquipmentUrl updateEquipmentURL(FEFEquipmentUrl url);
    
    public boolean deleteEquipmentURL(String systemId);
    
    public FEFEquipmentUrl getEquipmentURL(String systemId);
       
    public List<FEFEquipmentUrl> getAllEquipmentURL();
    
    
    
    
    
    
}
