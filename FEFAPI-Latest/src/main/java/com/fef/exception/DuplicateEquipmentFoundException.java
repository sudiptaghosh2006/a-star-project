package com.fef.exception;

public class DuplicateEquipmentFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;  
    public DuplicateEquipmentFoundException(String message)
    {
	super(message);
    }
}
