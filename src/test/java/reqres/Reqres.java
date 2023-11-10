package reqres;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;



public class Reqres 
{
	int id;
	
	@Test(priority = 1)
	void getUser()
	{
		given()
			

		.when()
			.get("https://reqres.in/api/users?page=2")
			
		.then()
		   .statusCode(200)
		   .log().all();
		   
	}
	
	@Test(priority = 2)
	void createUser()
	{
		   Pojo data = new Pojo();
		   data.setName("Sanju");
		   data.setJob("Gammer");
		
		id=given()
			.accept("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	
	@Test(priority = 3,dependsOnMethods ="createUser")
	void updateUser()
	{
		   Pojo data = new Pojo();
		   data.setName("Sanjay");
		   data.setJob("Gammer");
		
		given()
			.accept("application/json")
			.body(data.toString())
		
		.when()
			.post("https://reqres.in/api/users/+id")
			
		
		.then()
			.statusCode(201)
			.log().all();
	}
	
	@Test(priority = 4)
	void deleteUser()
	{
		given()
		
		.when()
		.delete("https://reqres.in/api/users/+id")
		
		.then()
		   .statusCode(204);
	}


}
