package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.user;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDtests {

	@Test(priority = 0, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostuser(String userID, String username, String fname, String lname, String useremail, String pwd,
			String ph) { // user class construction
		user userPayload = new user();

		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(username);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		Response response = UserEndPoints.createUSer(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);

	}

//	@Test( priority =1,dataProvider = "usernames", dataProviderClass = DataProviders.class)
	public void testUpateUserbyName(String fname,String lname,String ph,String username)
		
	{
		
		user userPayload =new user();
		
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname)	;
		userPayload.setPhone(ph);		
		
		Response response = UserEndPoints.updateUser(username,userPayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		response.getBody().prettyPrint();

		
	}
	
	
	
	
	
	//@Test(priority =2, dataProvider ="usernames", dataProviderClass = DataProviders.class)
	public void testGetUserByName(String username) {
		Response response = UserEndPoints.readUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);

	}
	
	
	

	@Test(priority = 3, dataProvider = "usernames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String username) {

		Response response = UserEndPoints.deleteUser(username);

		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
