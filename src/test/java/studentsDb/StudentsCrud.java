package studentsDb;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class StudentsCrud 
{
	int id;
		
   @Test(priority = 1)
	void CreateUser()
	{
	    PojoStudents data  = new PojoStudents();
	    
	    data.setName("Simran");
	    data.setLocation("Brazil");
	    data.setPhone("84764676555");
	    String coursesArr[] = {"DataScience","MachineLearning"};
	    data.setCourses(coursesArr);
		
		
		id=given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/Students")
			.jsonPath().getInt("id");		
//		.then()
//			
//         .statusCode(201)		   
//         .log().all();
		     
		
	}
	
	
	@Test(priority = 2,dependsOnMethods ="CreateUser")
		void updateUser()
		{
		    PojoStudents data  = new PojoStudents();
		    
		    data.setName("Simran jain");
		    data.setLocation("London");
		    data.setPhone("8435676555");
		    String coursesArr[] = {"DataScience","MachineLearning"};
		    data.setCourses(coursesArr);
			
			
			given()
				.contentType("application/json")
				.body(data)
			
			.when()
				.put("http://localhost:3000/Students/"+id)
					
			.then()
				
	         .statusCode(200);		   
	         
			     
			
		}
	
	@Test(priority = 3)
	void getUser()
	{
		given()

	   .when()
		  .get("http://localhost:3000/Students/"+id)

		.then()
		  .statusCode(200)
		  .log().body();
		   

	}
	
	
	@Test(priority = 4)
	void delete()
	{
		given()
		
		.when()
		  .delete("http://localhost:3000/Students/"+id)
		
		.then()
		   .statusCode(200);
		   
		
	}
	
	@Test(priority = 5,dependsOnMethods = "delete")
	void getAllUser()
	{
		given()

	   .when()
		  .get("http://localhost:3000/Students")

		.then()
		  .statusCode(200)
		   .log().all();

	}
	
	

}
