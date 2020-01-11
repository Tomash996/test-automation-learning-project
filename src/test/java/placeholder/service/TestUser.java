package placeholder.service;

import static org.softserve.service.placeholder.PlaceholderApi.placeholderApi;

import org.softserve.models.user.AddressDTO;
import org.softserve.models.user.CompanyDTO;
import org.softserve.models.user.UserDTO;
import org.softserve.testcases.common.BaseServiceTestCase;
import org.testng.annotations.Test;

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

}
