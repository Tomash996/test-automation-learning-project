package org.softserve.service.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.softserve.models.user.UserDTO;
import org.softserve.service.common.AbstractWebEndpoint;

public class UserEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PATH_USER = "users";

    public UserEndpoint(RequestSpecification requestSpecification){
        this.requestSpecification = requestSpecification;
    }

    public UserDTO createNewUser(UserDTO payload) {
        ValidatableResponse validatableResponse = createNewUser(
            payload,
            HttpStatus.SC_CREATED);
        return validatableResponse.extract().as(UserDTO.class);
    }

    public ValidatableResponse createNewUser(UserDTO payload, int status) {
        return this.createUser(payload).statusCode(status);
    }

    private ValidatableResponse createUser(UserDTO payload) {
        LOGGER.info("Creating new User:\n");
        return this.post(
            requestSpecification,
            PATH_USER,
            payload);
    }


}
