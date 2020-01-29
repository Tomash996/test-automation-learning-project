package org.softserve.service.placeholder;

import io.restassured.specification.RequestSpecification;
import org.softserve.service.common.AbstractWebService;

import org.softserve.service.placeholder.endpoints.UserEndpoint;

public class PlaceholderApi extends AbstractWebService {

    private static final String PLACEHOLDER_SETTING = "JSONPLACEHOLDER_URL";

    private UserEndpoint userEndpoint;

    private static ThreadLocal<PlaceholderApi> webApi = new ThreadLocal<>();

    protected PlaceholderApi() {
        super(PLACEHOLDER_SETTING);
    }

    public static PlaceholderApi placeholderApi() {
        if (webApi.get() == null) {
            initPlaceholderApi();
        }
        return webApi.get();
    }

    private static void initPlaceholderApi() {
        webApi.set(new PlaceholderApi());
    }

    @Override
    protected void initEndpoints() {
        RequestSpecification requestSpecification = getDefaultSpecification();

        initUserEndpoint(requestSpecification);
    }

    private void initUserEndpoint(RequestSpecification requestSpecification) {
        userEndpoint = new UserEndpoint(requestSpecification);
    }

    public UserEndpoint user() {
        return userEndpoint;
    }

}
