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
    private static final String PATH_USERS = "users";
    private static final String PATH_USER_RESOURCE = "users/{user_Id}";


    public UserEndpoint(RequestSpecification requestSpecification) {
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
                PATH_USERS,
                payload);
    }


    public UserDTO updateUser(String userId, UserDTO payload) {
        ValidatableResponse validatableResponse = updateUser(
                userId,
                payload,
                HttpStatus.SC_OK);
        return validatableResponse.extract().as(UserDTO.class);
    }

    public ValidatableResponse updateUser(String userId, UserDTO payload, int status) {
        return this.updateUserNoValidation(userId, payload).statusCode(status);
    }

    private ValidatableResponse updateUserNoValidation(String userId, UserDTO userData) {
        LOGGER.info("Update User:\n");
        return this.put(
                requestSpecification,
                PATH_USER_RESOURCE,
                userData,
                userId);
    }

    public UserDTO retrieveUserByID(String userId) {
        ValidatableResponse validatableResponse = retrieveUserByID(
                userId,
                HttpStatus.SC_OK);
        return validatableResponse.extract().as(UserDTO.class);
    }

    public ValidatableResponse retrieveUserByID(String userId,  int status) {
        return this.retrieveUserNoValidation(userId).statusCode(status);
    }

    private ValidatableResponse retrieveUserNoValidation(String userId) {
        LOGGER.info("Retrieve User Info:\n");
        return this.get(
                requestSpecification,
                PATH_USER_RESOURCE,
                userId);
    }

 public UserDTO[] getUserList(){
        ValidatableResponse validatableResponse = getUserList(
                200);
 return validatableResponse.extract().as(UserDTO[].class);
    }


    public ValidatableResponse getUserList(int status) {
        return this.getUserListNoValidation().statusCode(status);
    }

    private ValidatableResponse getUserListNoValidation() {
        LOGGER.info("Get Users List :\n");
        return this.get(
                requestSpecification,
                PATH_USERS);
    }

}