package com.fef.access;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.fef.common.dto.UserType;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.annotation.PostConstruct;

@Service
public class AccessPlanService
{

    private Bucket userBucket;
    private Bucket adminBucket;
    private Bucket othersBucket;

    public Bucket getServicePlanBucket(String userType)
    {
	return getBandwidthForUserType(userType);
    }

    private Bucket getBandwidthForUserType(String userType)
    {
	if (userType.equalsIgnoreCase(UserType.USER))
	{
	    return userBucket;
	}
	else if (userType.equalsIgnoreCase(UserType.ADMIN))
	{
	    return adminBucket;
	}
	else
	    return othersBucket;
    }

    @PostConstruct
    private void initializeBandwidth()
    {
	userBucket = Bucket4j.builder().addLimit(Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(10))))
		.build();
	adminBucket = Bucket4j.builder().addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1))))
		.build();
	othersBucket = Bucket4j.builder().addLimit(Bandwidth.classic(2, Refill.intervally(2, Duration.ofMinutes(1))))
		.build();
    }

}