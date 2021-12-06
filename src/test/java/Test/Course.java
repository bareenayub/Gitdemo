package Test;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import Files.getDataBody;
@Test
public class Course {
    JsonPath js= new JsonPath(getDataBody.coursetest());
    @Test
    public void countCourse()
    {
        int count= js.getInt("courses.size()");
        System.out.println("no of courses : " +count);


    }

    @Test
    public void purchaseAmount()
    {
     int purchase=js.getInt("dashboard.purchaseAmount");
        System.out.println(purchase);
    }

    @Test
    public void getIstIndex()
    {
        String title=js.get("courses[0].title");
        System.out.println(title);
    }
    @Test
    public  void allCoursesandPrices()
    {
        int i;
        for(i=0;i<3;i++)
        {
            String courseTitle=js.get("courses["+i+"].title");
            System.out.println(courseTitle);
            System.out.println(js.get("courses["+i+"].price").toString());
        }

    }

    @Test
    public void getParticulartitleCopies()
    {
        int i;
        int count=js.getInt("courses.size()");
        for(i=0;i<count;i++) {
            String courseTitle=js.get("courses["+i+"].title");
            if(courseTitle.equalsIgnoreCase("RPA"))
            {
                int copies=js.get("courses["+i+"].copies");
                System.out.println(copies);
                break;

            }

        }

    }

    @Test
    public void sumAllCopiesValidation()
    {
        int i, sumall=0;
        int count=js.getInt("courses.size()");
        for(i=0;i<count;i++) {
            String courseTitle = js.get("courses[" + i + "].title");
            int price=js.getInt("courses["+i+"].price");
            int copies=js.getInt("courses["+i+"].copies");
            System.out.println(courseTitle + ":"+ price*copies);
            sumall+=(price*copies);
        }
         int total=js.get("dashboard.purchaseAmount");
        System.out.println("Price of purchase Amount :" + total);
        System.out.println("Sum all titles with no of copies n price :" + sumall);
        Assert.assertEquals(sumall,total);
    }
}
