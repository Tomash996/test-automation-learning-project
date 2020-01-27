package placeholder.service;

import static org.softserve.service.placeholder.PlaceholderApi.placeholderApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.softserve.models.user.AddressDTO;
import org.softserve.models.user.CompanyDTO;
import org.softserve.models.user.UserDTO;
import org.softserve.service.placeholder.endpoints.InvalidIdEndpoint;
import org.softserve.testcases.common.BaseServiceTestCase;
import org.testng.annotations.Test;

import java.util.List;

public class TestUser extends BaseServiceTestCase {

    private UserDTO getUserPayload(){
        AddressDTO address = new AddressDTO()
            .setCity("London")
            .setStreet("Blackwood Str")
            .setSuite("TestSuite")
            .setZipcode("AF 90009");
        CompanyDTO company = new CompanyDTO()
            .setBs("Test BS")
            .setName("Johnson & Johnson")
            .setCatchPhrase("Test Phrase");
        return new UserDTO()
            .setName("David")
            .setUsername("DavidJohnson")
            .setEmail("test@email.com")
            .setAddress(address)
            .setPhone("0212896652")
            .setWebsite("www.Johnson.com")
            .setCompany(company);
    }


    private UserDTO getUserID(){
      return new UserDTO()
              .setId("12");
    }

    @Test(testName = "C00001",
        description = "Placeholder: User: Verify ability to create new user",
        groups = {"api", "regression"})

    public void testVerifyAbilityToCreateNewUser() {
        UserDTO payloadData = getUserPayload();
        UserDTO userResponse = placeholderApi().user().createNewUser(payloadData);

        softAssert.assertThat(userResponse)
            .usingRecursiveComparison()
            .ignoringActualNullFields()
            .ignoringAllOverriddenEquals()
            .ignoringFields("id")
            .isEqualTo(payloadData);
        softAssert.assertAll();
    }




    @Test(testName = "C00003",
            description = "Placeholder: User: Verify ability to change User ID to invalid value",
            groups = {"api", "regression"})
    public void testVerifyUpdateUser(){
        UserDTO payloadData = getUserPayload();
        UserDTO userResponse = placeholderApi().user().createNewUser(payloadData);

        UserDTO updatedUserPayload = payloadData
                .setName("Updated User Name")
                .setCompany(new CompanyDTO().setName("Test Updated Company"));

        UserDTO updatedUserResponse = placeholderApi().user().updateUser("10", updatedUserPayload);
        updatedUserPayload.setId("10");

        softAssert.assertThat(updatedUserResponse)
                .usingRecursiveComparison()
                .ignoringActualNullFields()
                .ignoringAllOverriddenEquals()
                .isEqualTo(updatedUserPayload);
        softAssert.assertAll();

    }


    @Test(testName = "C00005",
            description = "Placeholder: User: Verify ability to change User ID to invalid value",
            groups = {"api", "regression"})
    public void testVerifyUpdateUserByInvalidId(){
        UserDTO payloadData = getUserPayload();
        UserDTO updatedUserPayload = payloadData
                .setName("Updated User Name")
                .setCompany(new CompanyDTO().setName("Test Updated Company"));
        String invalidId = "11";
        ValidatableResponse errorResponse =  placeholderApi().user().updateUser(invalidId, updatedUserPayload,404);


        softAssert.assertThat(errorResponse.extract().asString())
                .contains("Invalid User Id Provided");
        softAssert.assertAll();

    }




}
