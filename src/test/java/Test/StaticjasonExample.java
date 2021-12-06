package Test;

import Files.getDataBody;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StaticjasonExample {
    //content of th file to String ist, convert data into byte which can be converted into string
    @Test
    public void addBook() throws IOException {
        //adding book , onnce  created you cannot create on same.
        RestAssured.baseURI="http://216.10.245.166";
        String response= given().log().all().header("Content-Type","application/json").
                body(new String(Files.readAllBytes(Paths.get("src/test/AddPlace.json"))) ).
                when().post("/Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath ps=new JsonPath(response);

        System.out.println(ps);
    }
}
