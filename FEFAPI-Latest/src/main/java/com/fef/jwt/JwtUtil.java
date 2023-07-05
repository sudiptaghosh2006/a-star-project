package com.fef.jwt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fef.controller.FEFEquipmentController;
import com.fef.exception.TokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil
{

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    
//    Locale locale = new Locale.Builder().setLanguage("en").setRegion("US").build();
//    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
    DateFormat dateFormat =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    
    private String SECRET_KEY = "secret";

    public String extractUsername(String token)
    {
	return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token)
    {
	Date extractClaim = extractClaim(token, Claims::getExpiration);	
	LOGGER.debug("Get Expiration  ==> {}",dateFormat.format(extractClaim));
	return extractClaim;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
	try
	{
	    final Claims claims = extractAllClaims(token);
//	    claims.forEach(null);
	    return claimsResolver.apply(claims);
	}
	catch (Exception e)
	{
	    throw new TokenException(e.getMessage());
	}
    }

    private Claims extractAllClaims(String token)
    {
	Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	LOGGER.debug("Claims  ==> {}",claims);
	return claims;
	
    }

    public Boolean isTokenExpired(String token)
    {
	return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName)
    {
	Map<String, Object> claims = new HashMap<>();
	return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String subject)
	{
	   	   
	    Date dateIssue = new Date(System.currentTimeMillis());
	    Date dateExp = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);
//	    Date dateExp = new Date(System.currentTimeMillis() + 1000 );
	   
	    LOGGER.debug("issue date ==>"+dateFormat.format(dateIssue)); 
	    LOGGER.debug("Exp date ==>"+dateFormat.format(dateExp)); 

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(dateIssue)
				.setExpiration(dateExp)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

    public Boolean validateToken(String token, String userName)
    {
	final String extractedUserName = extractUsername(token);
	return (extractedUserName.equals(userName) && !isTokenExpired(token));
    }

}