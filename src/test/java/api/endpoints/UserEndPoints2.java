package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//User EndPoints.java
//Created for perform Create,Read,Update and Delete requests to the user API( CRUD operations)


//this calss for Propertyfile implementation

public class UserEndPoints2 {
	
	//Extra method for getting url from routes properties file
	static ResourceBundle  getURL(){
		
		ResourceBundle routes =ResourceBundle.getBundle("routes");//Load Properties file //name of the property file is routes
		return routes;
		
	}
	
	public static Response createUSer(user payload)
	
	//using user clas pojo objects here to refere payload
	{		
		
		String post_url = getURL().getString("post_url");
		
	Response response = 	
		given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
           .body(payload)
		.when()
		.post(post_url);
	return response;
	
	}
	public static Response readUser(String username )	
	
	{	
		
		String get_url = getURL().getString("get_url");
		
		Response response = given()
				   .pathParam("username", username)
				.when()
				.get(get_url);
		return response;
	}
public static Response updateUser(String username ,user payload)	
	
	{	
	String updata_url = getURL().getString("updata_url");
		Response response = given()
				.contentType(ContentType.JSON)
				   .accept(ContentType.JSON)		           
				  .pathParam("username", username)
				  .body(payload)
				.when()
				.put(updata_url);
		return response;
	}	
public static Response deleteUser(String username )
{	
	String delete_url = getURL().getString("delete_url");
	
	Response response = given()
			  .pathParam("username", username)
			.when()
			.delete(delete_url);
	return response;
}	
		

}
