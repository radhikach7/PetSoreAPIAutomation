package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.orgutilities.ORGDataProviders;
import api.payload.user;
import io.restassured.response.Response;

public class DataDTests {
	

	



	    @Test(priority = 0, dataProvider = "userData", dataProviderClass = ORGDataProviders.class)
	    public void testPostUser(String userID, String username, String firstName, String lastName,
	                             String email, String password, String phone) {
	    	user userPayload = new user();
	        userPayload.setId(Integer.parseInt(userID));
	        userPayload.setUsername(username);
	        userPayload.setFirstName(firstName);
	        userPayload.setLastName(lastName);
	        userPayload.setEmail(email);
	        userPayload.setPassword(password);
	        userPayload.setPhone(phone);

	        Response response = UserEndPoints.createUSer(userPayload);
	        Assert.assertEquals(response.getStatusCode(), 200);
	    }

	    @Test(priority = 1, dataProvider = "usernames", dataProviderClass = ORGDataProviders.class)
	    public void testDeleteUser(String username)
	    
	    
	    {
	        Response response = UserEndPoints.deleteUser(username);
	        Assert.assertEquals(response.getStatusCode(), 200);
	    }
	}

	
	
	
	





