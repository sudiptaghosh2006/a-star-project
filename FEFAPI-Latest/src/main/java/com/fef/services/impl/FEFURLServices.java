package com.fef.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fef.model.FEFEquipmentUrl;
import com.fef.repositories.IEquipmentUrlRepository;
import com.fef.services.IOEMURLServices;

import jakarta.annotation.PostConstruct;

@Service
@Transactional
public class FEFURLServices implements IOEMURLServices
{
    @Autowired
    private IEquipmentUrlRepository repository;
    private Map<String, String> urlMap;

    @Override
    public String getLegacyURL()
    {
	return urlMap.get("LEGACY");
    }

    @Override
    public String getSemiConnextURL()
    {	
	return urlMap.get("SEMI_CONNEXT");
    }
    
    @PostConstruct
    private void retrieveSystemURL() {
	
	List<FEFEquipmentUrl> listUrl = (List<FEFEquipmentUrl>) repository.findAll();
	urlMap = listUrl.stream().collect(Collectors.toMap(url->url.getSystemId(),url->url.getSystemUrl()));
    }

}
