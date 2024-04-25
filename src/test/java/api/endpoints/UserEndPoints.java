package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//User EndPoints.java
//Created for perform Create,Read,Update and Delete requests to the user API( CRUD operations)

public class UserEndPoints {
	
	public static Response createUSer(user payload)
	
	//using user clas pojo objects here to refere payload
	{		
	Response response = 	
		given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
           .body(payload)
		.when()
		.post(routes.post_url);
	return response;
	
	}
	public static Response readUser(String username )	
	
	{	
		Response response = given()
				   .pathParam("username", username)
				.when()
				.get(routes.get_url);
		return response;
	}
public static Response updateUser(String username ,user payload)	
	
	{	
		Response response = given()
				.contentType(ContentType.JSON)
				   .accept(ContentType.JSON)		           
				  .pathParam("username", username)
				  .body(payload)
				.when()
				.put(routes.updata_url);
		return response;
	}	
public static Response deleteUser(String username )
{	
	Response response = given()
			  .pathParam("username", username)
			.when()
			.delete(routes.delete_url);
	return response;
}	
		
		

	
	
	
	
	
	
	
	
	

}
