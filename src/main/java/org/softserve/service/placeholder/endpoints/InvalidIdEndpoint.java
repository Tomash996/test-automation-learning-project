package org.softserve.service.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.softserve.models.user.UserDTO;
import org.softserve.service.common.AbstractWebEndpoint;

public class InvalidIdEndpoint extends AbstractWebEndpoint  {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PATH_INVALID = "users/invalId";


    public InvalidIdEndpoint(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }


   public UserDTO changeUserID(UserDTO id) {
       ValidatableResponse validatableResponse = changeUserID(
               id,
               HttpStatus.SC_NOT_FOUND);
       return validatableResponse.extract().as(UserDTO.class);

   }


   public ValidatableResponse changeUserID (UserDTO id, int status) {
       return this.changeUserInvalidID(id).statusCode(status);
   }


   private ValidatableResponse changeUserInvalidID (UserDTO id) {
        LOGGER.info("change invalid user ID \n");


       return this.put (
               requestSpecification,
               PATH_INVALID,
               id);
   }
   }



