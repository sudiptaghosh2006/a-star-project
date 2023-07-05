package com.fef.exception;

public class EquipmentNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;  
    public EquipmentNotFoundException(String message)
    {
	super(message);
    }

}
