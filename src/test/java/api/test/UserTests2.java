package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.user;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	
	user userPayload;
	public Logger logger; //for
	
	@BeforeClass
	public void setupData() {
		faker= new Faker()	;
		//pojoclass obj
		userPayload =new user()	;
		
		
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//logger 
		
		logger = LogManager.getLogger(this.getClass());
	
	}
	@Test(priority =0)
	public void testPostuser() {
		logger.info("*********Creating user ***********");
		Response response = UserEndPoints2.createUSer(userPayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.statusCode(), 200);
		logger.info("*********Created user ***********");
	}
	@Test(priority =1)
	public void testGetUserByName() {

		logger.info("*********getting userdata***********");
		
	Response response = 	UserEndPoints2.readUser(this.userPayload.getUsername());
		
	response.then().log().all();
	
	AssertJUnit.assertEquals(response.getStatusCode(),200);
	
	logger.info("*********Got user ***********");
	
	}
	@Test(priority =2)
	public void testUpdateuserByName() {
	
		
		logger.info("*********Updating user ***********");
		
		//put means update the payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		
	Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		
	response.then().log().all();
	
	
	
	AssertJUnit.assertEquals(response.getStatusCode(),200);
	//check the data ater update the 
	Response responseAfterUpdate = 	UserEndPoints2.readUser(this.userPayload.getUsername());
	
	response.then().log().all();
	
	AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(),200);
	
	logger.info("*********Updated user ***********");
	}
	
	@Test(priority =3)
	
	
	public void testDeleteUserbyName() {
		logger.info("*********Deleting user ***********");
		
		{
			
			Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
			AssertJUnit.assertEquals(response.getStatusCode(), 200);
			
			
		}
		logger.info("*********user Deleted  ***********");
	}
	

}
