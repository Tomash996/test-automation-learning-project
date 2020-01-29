package org.softserve.service.common;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class AbstractWebEndpoint {

    protected RequestSpecification requestSpecification;

    public AbstractWebEndpoint() {
        //default empty constructor
    }

    public AbstractWebEndpoint(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
        //default empty constructor
    }

    @SuppressWarnings("unchecked")
    public <T> T post(String path, Object bodyPayload, Class<T> responseClass, Object... pathParams) {
        return this.post(path, bodyPayload, pathParams)
            .extract().as((Class<T>) responseClass.getClass(), ObjectMapperType.JACKSON_2);
    }

    public ValidatableResponse post(String path, Object bodyPayload, Object... pathParams) {
        return this.post(this.requestSpecification, path, bodyPayload, pathParams);
    }

    public ValidatableResponse post(RequestSpecification requestSpecification, String path, Object bodyPayload,  Object... pathParams) {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.addRequestSpecification(requestSpecification);

        if (bodyPayload != null) {
            specBuilder.setBody(bodyPayload);
        }
        return given()
            .spec(specBuilder.build())
            .when()
            .post(path, pathParams)
            .then();
    }


    public ValidatableResponse put(String path, Object bodyPayload, Object... pathParams){
        return this.post(this.requestSpecification, path, bodyPayload, pathParams);
    }

    public ValidatableResponse put(RequestSpecification requestSpecification, String path, Object bodyPayload,  Object... pathParams) {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.addRequestSpecification(requestSpecification);

        if (bodyPayload != null) {
            specBuilder.setBody(bodyPayload);
        }

        return given()
                .spec(specBuilder.build())
                .when()
                .put(path, pathParams)
                .then();
    }


    public ValidatableResponse get(String path, Object bodyPayload, Object... pathParams){
        return this.get(this.requestSpecification, path, bodyPayload, pathParams);
    }

    public ValidatableResponse get(RequestSpecification requestSpecification, String path, Object bodyPayload,  Object... pathParams) {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.addRequestSpecification(requestSpecification);

        if (bodyPayload != null) {
            specBuilder.setBody(bodyPayload);
        }

        return
                given()
                .spec(specBuilder.build())
                .when()
                .get(path, pathParams)
                .then();

    }

}
