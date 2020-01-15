package org.softserve.service.common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.softserve.utils.PropertiesLoader;

public abstract class AbstractWebService {

    private String serviceUrl;

    protected abstract void initEndpoints();

    protected AbstractWebService(String apiSetting) {
        loadApiConfig(apiSetting);
        initEndpoints();
    }

    private void loadApiConfig(String apiSetting) {
         serviceUrl = PropertiesLoader.loadProjectProperties().getSetting(apiSetting);
    }

    protected RequestSpecification getDefaultSpecification() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder()
            .setBaseUri(serviceUrl)
            .setContentType(ContentType.JSON);
        return specBuilder.build();
    }

}
