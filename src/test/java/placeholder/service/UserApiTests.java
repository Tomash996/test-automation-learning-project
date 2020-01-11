package placeholder.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.softserve.models.user.UserDTO;
import org.softserve.utils.PropertiesLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserApiTests {

    // TODO should be refactored
    //  All tests need to move to TestUser class using example test in this class

    @BeforeClass
    public void setBaseUri() {
       PropertiesLoader.loadProjectProperties();
    }

    @Test(testName = "TC-01",
            description = "Invalid UserID verification",
            groups = {"api", "regression"})
    public void updateInvalidUserID() {

        //   String jsonString = "{\r\n" + " \"id\" : \"12\",\r\n" + "}";

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", 12);

        request.body(requestParams.toString());
        Response response = request.put("/invalId");
        int statusCode = response.getStatusCode();

        ResponseBody body = response.getBody();
        System.out.println("Response Body is: " + body.asString());

        assertEquals(statusCode, 404);
        assertTrue(body.asString().contains("Invalid User Id Provided"));

    }

    @Test (testName = "TC-02",
            description = "New User verification",
            groups = {"api", "regression"})

    public void verifyNewUser() {

        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject(UserDTO.createUser());
        request.body(requestParams);
        Response response = request.post();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }


    @Test (testName = "TC-03",
            description = "User info verification",
            groups = {"api", "regression"})

    public void verifyUserInfo() {
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        Response response = request.get("/1");

        int statusCode = response.getStatusCode();
        String name = "Leanne Graham";
        String username = "Bret";
        String city = "Gwenborough";

        JsonPath jsonPathValidator = response.jsonPath();
        ResponseBody body = response.getBody();
        System.out.println("Response Body is: " + body.asString());

        assertEquals(statusCode, 200);
        assertTrue(jsonPathValidator.get("name").equals(name));
        assertTrue(jsonPathValidator.get("username").equals(username));
        assertTrue(jsonPathValidator.get("address.city").equals(city));

    }

    @Test (testName = "TC-04",
            description = "User List  verification",
            groups = {"api", "regression"})
    public void verifyUserList() {
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        Response response = request.get();

        JsonPath jsonPathValidator = response.jsonPath();
        System.out.println("ID : \n" + jsonPathValidator.get("id"));

        List<String> jsonResponseRoot = jsonPathValidator.getList("$");
        System.out.println("Number of Employees : " + jsonResponseRoot.size());

        assertTrue(jsonResponseRoot.size() > 5);

    }


    @Test(testName = "TC-05",
            description = "Valid UserID  verification",
            groups = {"api", "regression"})

    public void updateValidUserID() {

        //    String jsonString = "{\r\n" + " \"id\" : \"2\",\r\n" + "}";

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", 1);
        request.body(requestParams.toString());

        Response response = request.put("/validId");

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200);
    }

}
