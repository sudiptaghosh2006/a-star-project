package com.eff.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fef.common.dto.ApplicationResponseData;
import com.fef.common.dto.UserType;
import com.fef.controller.FEFUserController;
import com.fef.model.AStarApplicationUser;
import com.fef.services.IUserServices;

@ExtendWith(MockitoExtension.class)
class FEFUserControllerTest
{

    @InjectMocks
    private FEFUserController userController;
    
    @Mock
    private IUserServices userServices;

    @Test
    void testGetUser()
    {
	List<AStarApplicationUser> list = new ArrayList<AStarApplicationUser>();
	AStarApplicationUser user1 = new AStarApplicationUser().setUserName("Sudipta").setUserType(UserType.ADMIN);
	AStarApplicationUser user2 = new AStarApplicationUser().setUserName("Sudipta123").setUserType(UserType.ADMIN);
	list.add(user1);
	list.add(user2);
        when(userServices.getUser("Sudipta")).thenReturn(user1);

        ResponseEntity<ApplicationResponseData<AStarApplicationUser>> user = userController.getUser("Sudipta");
        assertThat(user.getBody().getResponseData().getUserName()).isEqualTo("Sudipta");
        assertThat(user.getBody().getResponseStatus().getResponseCode()).isEqualTo(0);
        assertThat(user.getBody().getResponseStatus().getDetailMessageList()).isEqualTo(null);   
    }

}
