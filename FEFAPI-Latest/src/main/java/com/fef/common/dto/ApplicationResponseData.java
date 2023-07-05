package com.fef.common.dto;

public class ApplicationResponseData<T>
{
    private ServiceResponseStatus responseStatus;
    private T responseData;


    public T getResponseData()
    {
        return responseData;
    }

    public ApplicationResponseData<T> setResponseData(T responseData)
    {
        this.responseData = responseData;
        return this;
    }

    public ServiceResponseStatus getResponseStatus()
    {
	return responseStatus;
    }

    public ApplicationResponseData<T> setResponseStatus(ServiceResponseStatus responseStatus)
    {
	this.responseStatus = responseStatus;
	return this;
    }

}
