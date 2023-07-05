package com.fef.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fef.services.IOEMURLServices;

@Service
@Transactional
public class OEMURLServices implements IOEMURLServices
{
    
    @Value("${equipment.semiconnext.url}")
    private String semiConnextURL;

    @Value("${equipment.legacy.url}")
    private String legacyURL;

    @Override
    public String getLegacyURL()
    {
	return legacyURL;
    }

    @Override
    public String getSemiConnextURL()
    {
	return semiConnextURL;
    }

}
