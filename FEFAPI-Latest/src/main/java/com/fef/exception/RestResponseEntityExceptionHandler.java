package com.fef.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fef.common.dto.ServiceResponseStatus;
import com.fef.common.dto.ServiceResponseStatusConstant;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(value = { EquipmentNotFoundException.class,UserNotFoundException.class ,UrlNotFoundException.class,TokenException.class,DuplicateEquipmentFoundException.class })
    protected ResponseEntity<ServiceResponseStatus> handleEquipmentServiceException(RuntimeException ex,
	    WebRequest request)
    {

	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.ERROR_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.ERROR_MESSAGE);
	responseStatus.addDetailMessage(ex.getMessage());

	return new ResponseEntity<ServiceResponseStatus>(responseStatus, HttpStatus.NOT_FOUND);
    }   

}