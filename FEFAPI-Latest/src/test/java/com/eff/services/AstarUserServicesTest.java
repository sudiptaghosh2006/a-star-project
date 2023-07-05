package com.eff.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fef.common.dto.UserType;
import com.fef.model.AStarApplicationUser;
import com.fef.repositories.IUserRepository;
import com.fef.services.impl.AstarUserServices;


@ExtendWith(MockitoExtension.class)
class AstarUserServicesTest
{
    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    private AstarUserServices userServices;


    @Test
    void testGetUser()
    {
	String userId = "Sudipta";
	String userId2 = "Sudipta2";
	AStarApplicationUser user1 = new AStarApplicationUser().setUserName(userId)
		.setUserType(UserType.ADMIN);

	Optional<AStarApplicationUser> optional=Optional.of(user1);
//	Optional<AStarApplicationUser> optional2=Optional.of(null);
	when(userRepository.findById(userId)).thenReturn(optional);
	
//	when(userRepository.findById(userId2)).thenReturn(optional2);
	// test
	AStarApplicationUser user = userServices.getUser(userId);
	
//	AStarApplicationUser user2 = userServices.getUser(userId2);

	assertEquals(userId, user.getUserName());
	assertEquals(UserType.ADMIN, user.getUserType());

    }

}
