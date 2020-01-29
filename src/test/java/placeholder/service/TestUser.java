package placeholder.service;

import static org.softserve.service.placeholder.PlaceholderApi.placeholderApi;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.softserve.models.user.AddressDTO;
import org.softserve.models.user.CompanyDTO;
import org.softserve.models.user.UserDTO;
import org.softserve.testcases.common.BaseServiceTestCase;
import org.testng.annotations.Test;
import java.util.*;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class TestUser extends BaseServiceTestCase {


    private UserDTO getUserPayload() {
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

    private UserDTO getCustomId() {
        return new UserDTO()
                .setId("1");
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
    public void testVerifyUpdateUser() {
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
    public void testVerifyUpdateUserByInvalidId() {
        UserDTO payloadData = getUserPayload();
        UserDTO updatedUserPayload = payloadData
                .setName("Updated User Name")
                .setCompany(new CompanyDTO().setName("Test Updated Company"));
        String invalidId = "11";
        ValidatableResponse errorResponse = placeholderApi().user().updateUser(invalidId, updatedUserPayload, 404);

        softAssert.assertThat(errorResponse.extract().asString())
                .contains("Invalid User Id Provided");
        softAssert.assertAll();

    }


    @Test(testName = "C00006",
            description = "Placeholder: User:  Verify possibility to retrieve User by Id Scenario",
            groups = {"api", "regression"})
    public void testVerifyUserByID() {
        System.out.println("dfgdsgfhs ");
        ObjectMapper mapper = new ObjectMapper();
        UserDTO customId = getCustomId();
        UserDTO retrievedUserResponse = placeholderApi().user().retrieveUserByID("1", customId);

        try {
            UserDTO userDTO = mapper.readValue(retrievedUserResponse.toString(), UserDTO.class);
            System.out.println("UserDTO " + userDTO);
        } catch (Exception e) {
            System.out.println("FAILED");
        }

        softAssert.assertThat(retrievedUserResponse.toString())
                .contains("Leanne Graham")
                .contains("Bret");
        //  .contains("Gwenborough");
        softAssert.assertAll();
    }

    @Test(testName = "C00007",
            description = "Placeholder: User: Verify that User List contains more than 5 items",
            groups = {"api", "regression"})
    public void testVerifyUserList() {
        ValidatableResponse userListResponse = placeholderApi().user().getUserList(200);
        JsonPath jsonPathValidator = userListResponse.extract().jsonPath();
        ArrayList recievedUserList  = jsonPathValidator.get("id");
        System.out.println(recievedUserList);

        softAssert.assertThat(recievedUserList)
                .hasSizeGreaterThan(5);
        softAssert.assertAll();

    }
}




