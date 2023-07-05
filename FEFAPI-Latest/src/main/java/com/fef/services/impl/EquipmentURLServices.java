package com.fef.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fef.exception.UrlNotFoundException;
import com.fef.model.AStarEquipmentUrl;
import com.fef.repositories.IEquipmentUrlRepository;
import com.fef.services.IEquipmentURLServices;


@Service
@Transactional
public class EquipmentURLServices implements IEquipmentURLServices
{
    @Autowired
    private IEquipmentUrlRepository urlRepository;

    @Override
    public AStarEquipmentUrl createEquipmentURL(AStarEquipmentUrl url)
    {
	return urlRepository.save(url);
	

    }

    @Override
    public AStarEquipmentUrl updateEquipmentURL(AStarEquipmentUrl url)
    {
	AStarEquipmentUrl returnValue ;
	Optional<AStarEquipmentUrl> optional = urlRepository.findById(url.getId());
	if (optional.isPresent())
	{
	    AStarEquipmentUrl starEquipmentUrl = optional.get();
	    starEquipmentUrl.setActive(url.isActive());
	    starEquipmentUrl.setSystemUrl(url.getSystemUrl());
	    urlRepository.save(starEquipmentUrl);	    
	    returnValue = starEquipmentUrl;
	}
	else
	{
	    throw new UrlNotFoundException("equipment not found in the DB to delete id :: " + url.getId());
	}
	
	return returnValue;

    }

    @Override
    public boolean deleteEquipmentURL(Integer id)
    {
	boolean returnValue = false;
	Optional<AStarEquipmentUrl> optional = urlRepository.findById(id);
	if (optional.isPresent())
	{
	    AStarEquipmentUrl starEquipmentUrl = optional.get();
	    starEquipmentUrl.setActive(false);
	    urlRepository.save(starEquipmentUrl);
	    returnValue = true;
	    

	}
	else
	{
	    throw new UrlNotFoundException("Equipment URL not found in the DB to delete id :: " + id);
	}
	
	return returnValue;

    }

    @Override
    public AStarEquipmentUrl getEquipmentURL(Integer id)
    {
	Optional<AStarEquipmentUrl> optional = urlRepository.findById(id);
	if (optional.isPresent())
	{
	    return optional.get();
	}
	else
	{
	    throw new UrlNotFoundException("Equipment URL not found in the DB for id :: " + id);
	}
    }

    @Override
    public List<AStarEquipmentUrl> getAllEquipmentURL()
    {
	return (List<AStarEquipmentUrl>) urlRepository.findAll();

    }

}
