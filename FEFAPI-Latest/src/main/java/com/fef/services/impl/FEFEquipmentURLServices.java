package com.fef.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fef.exception.UrlNotFoundException;
import com.fef.model.FEFEquipmentUrl;
import com.fef.repositories.IEquipmentUrlRepository;
import com.fef.services.IEquipmentURLServices;


@Service
@Transactional
public class FEFEquipmentURLServices implements IEquipmentURLServices
{
    @Autowired
    private IEquipmentUrlRepository urlRepository;

    @Override
    public FEFEquipmentUrl createEquipmentURL(FEFEquipmentUrl url)
    {
	return urlRepository.save(url);
    }

    @Override
    public FEFEquipmentUrl updateEquipmentURL(FEFEquipmentUrl url)
    {
	FEFEquipmentUrl returnValue ;
	Optional<FEFEquipmentUrl> optional = urlRepository.findById(url.getSystemId());
	if (optional.isPresent())
	{
	    FEFEquipmentUrl starEquipmentUrl = optional.get();
	    starEquipmentUrl.setSystemUrl(url.getSystemUrl());
	    urlRepository.save(starEquipmentUrl);	    
	    returnValue = starEquipmentUrl;
	}
	else
	{
	    throw new UrlNotFoundException("equipment not found in the DB to delete id :: " + url.getSystemId());
	}
	
	return returnValue;

    }

    @Override
    public boolean deleteEquipmentURL(String systemId)
    {
	boolean returnValue = false;
	Optional<FEFEquipmentUrl> optional = urlRepository.findById(systemId);
	if (optional.isPresent())
	{
	    FEFEquipmentUrl starEquipmentUrl = optional.get();
	    urlRepository.delete(starEquipmentUrl);
	    returnValue = true;
	}
	else
	{
	    throw new UrlNotFoundException("Equipment URL not found in the DB to delete id :: " + systemId);
	}	
	return returnValue;

    }

    @Override
    public FEFEquipmentUrl getEquipmentURL(String systemId)
    {
	Optional<FEFEquipmentUrl> optional = urlRepository.findById(systemId);
	if (optional.isPresent())
	{
	    return optional.get();
	}
	else
	{
	    throw new UrlNotFoundException("Equipment URL not found in the DB for id :: " + systemId);
	}
    }

    @Override
    public List<FEFEquipmentUrl> getAllEquipmentURL()
    {
	return (List<FEFEquipmentUrl>) urlRepository.findAll();

    }

}
