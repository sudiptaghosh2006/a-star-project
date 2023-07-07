package com.fef.interceptor;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fef.access.AccessPlanService;
import com.fef.common.dto.ServiceResponseStatus;
import com.fef.common.dto.ServiceResponseStatusConstant;
import com.fef.jwt.JwtUtil;

import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor
{
    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";
    
    private static final String USER_TYPE = "user-type";

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Value("#{'${bypassUrls}'.split(',')}")   
     private List<String> bypassUrls;
    
    @Autowired
    private AccessPlanService plan;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
	boolean passed = rateLimitCheck(request.getHeader(USER_TYPE));
	if(!passed)
	{
	        ObjectMapper mapper = new ObjectMapper();
		ServiceResponseStatus responseStatus = new ServiceResponseStatus();
		responseStatus.setResponseCode(ServiceResponseStatusConstant.ERROR_CODE);
		responseStatus.setResponseMessage(ServiceResponseStatusConstant.ERROR_MESSAGE);
		responseStatus.addDetailMessage("Too many hits !!!!!!!!!");
		response.setContentType("application/json");
		response.setStatus(429);
		response.getWriter().write(mapper.writeValueAsString(responseStatus)); 
		return passed;
	}
	

	String authorizationHeader = request.getHeader(AUTHORIZATION) != null ? request.getHeader(AUTHORIZATION) : "";
	boolean isValid = true;
	String requestedURL = ((HttpServletRequest) request).getServletPath().toString();
	logger.debug("URL Requested ===>>> {}", requestedURL);
	logger.debug("authorizationHeader ===>>> {} ", authorizationHeader);
	logger.debug("In preHandle()");
	
//	if (requestedURL.contains(bypassUrls)) 
	if(bypassUrls.stream().anyMatch(byPassURL->requestedURL.contains(byPassURL)))
	{
	    isValid = true; 
	}
	
	else if (authorizationHeader != null && !authorizationHeader.isEmpty())
	{
	    if (!jwtUtil.isTokenExpired(authorizationHeader))
	    {
		isValid = true;
	    }

	    else
	    {
		ObjectMapper mapper = new ObjectMapper();
		ServiceResponseStatus responseStatus = new ServiceResponseStatus();
		responseStatus.setResponseCode(ServiceResponseStatusConstant.ERROR_CODE);
		responseStatus.setResponseMessage(ServiceResponseStatusConstant.ERROR_MESSAGE);
		responseStatus.addDetailMessage("token has expired");
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(mapper.writeValueAsString(responseStatus));
		isValid = false;
	    }
	}
	else
	{
	    ObjectMapper mapper = new ObjectMapper();
	    ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	    responseStatus.setResponseCode(ServiceResponseStatusConstant.ERROR_CODE);
	    responseStatus.setResponseMessage(ServiceResponseStatusConstant.ERROR_MESSAGE);
	    responseStatus.addDetailMessage("Request does not contain authorization token");
	    response.setContentType("application/json");
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    response.getWriter().write(mapper.writeValueAsString(responseStatus));
	    isValid = false;
	}

	return isValid;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception
    {
	logger.debug("In postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	    throws Exception
    {
	logger.debug("In afterCompletion() ");
    }
    
    
    private boolean rateLimitCheck(String userType)
    {
	Bucket bucket = plan.getServicePlanBucket(userType);
	System.out.println("AVAILABLE Token Count==>"+bucket.getAvailableTokens()  + " USER TYPE  ==>"+userType);
	System.out.println("Token==>"+bucket.toString()  + " USER TYPE  ==>"+userType);	
	return bucket.tryConsume(1);
	
    }

}
