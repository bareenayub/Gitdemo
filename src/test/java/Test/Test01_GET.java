package Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import Files.getDataBody;
public class Test01_GET {
//    @Test
//    public void get() {
////        https://reqres.in/api/users?page=2
//        //given - all input
//        //when - submit the API :recourse and http method goes in when
//        //then - validate the response
//
//        RestAssured.baseURI = "https://rahulshettyacademy.com";
//        //given method //query param //body
//        given().log().all().queryParam("key","qaclick123").
//                header("Content-Type", "application/json").body(getDataBody.checkBody()). //this is given
//        when().post("maps/api/place/add/json").prettyPeek().
//        then().log().all().assertThat().statusCode(200); //matching the response in body is same or not.
////                  header("Server","Apache/2.4.18 (Ubuntu)"); //here its check the output body
//
//    }
     // add place //update the data // get place to verify that update has taken place
   //put----------------
    @Test
    void add()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

       String response= given().log().all().queryParam("key","qaclick123").
                            header("Content-Type", "application/json").body(getDataBody.checkBody()). //this is given
                        when().post("maps/api/place/add/json").
                        then().assertThat().statusCode(200).body("scope",equalTo("APP")). //matching the response in body is same or not.
                              header("Server","Apache/2.4.18 (Ubuntu)").extract().response().asString();

      System.out.println("response is " +response);
        JsonPath js=new JsonPath(response);
      String placeId=js.getString("place_id");
        System.out.println(placeId); //get  placeid

        //update now
        String newAddress="love street";
        given().log().all().queryParam("key","qaclick123").
                body("{\r\n" +
                        "\"place_id\":\""+placeId+"\",\r\n" +
                        "\"address\":\""+newAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").
        when().put("maps/api/place/update/json").prettyPeek().
        then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));

        //now verify new address is changed or not
        //get-------------

      String getPlaceResponse= given().log().all().queryParam("keys","qaclick123").queryParam("place_id",placeId).
                             when().get("maps/api/place/get/json").
                             then().assertThat().log().all().statusCode(200).extract().response().asString();
        //System.out.println(getPlaceResponse);
      JsonPath js1=new JsonPath(getPlaceResponse);
      String actualAddress= js1.getString("address");
        System.out.println(actualAddress);
//     Assert.assertEquals(actualAddress,newAddress);
    }


}
