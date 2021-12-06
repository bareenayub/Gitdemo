package Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import Files.getDataBody;
@Test
public class DynamicJson {
    //parametrized api
    @Test(dataProvider = "BooksData")
    public void addBook(String name,String num)
    {
        //adding book , onnce  created you cannot create on same.
        RestAssured.baseURI="http://216.10.245.166";
        String response= given().log().all().header("Content-Type","application/json").
                body(getDataBody.AddBooks(name,num)).
        when().post("/Library/Addbook.php").
        then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath ps=new JsonPath(response);
        String id= ps.get("ID");
        System.out.println(id);
    }
    //addbook will be called as the number of arrays
 @DataProvider(name = "BooksData")
  public Object[][]  getData()
  {
//      new object[][] {array1,array2,array3};
      //create a multi D array of all objects
      return new Object[][]{{"bookname","675"},{"boknam2","453"},{"book name 3","898"}} ; //can mention arrays like this90give values like this)


  }
}
