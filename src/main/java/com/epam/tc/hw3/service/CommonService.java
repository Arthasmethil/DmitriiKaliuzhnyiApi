package com.epam.tc.hw3.service;

import static com.epam.tc.hw3.utils.Constants.CONTENT_TYPE;
import static io.restassured.RestAssured.given;

import com.epam.tc.hw3.utils.PropertiesProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.apache.http.HttpStatus;

public class CommonService {

    private final RequestSpecification REQUEST_SPECIFICATION;

    public CommonService() {
        PropertiesProvider properties = new PropertiesProvider();
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(properties.getProperty("base.url"))
            .addQueryParam("key", properties.getProperty("key"))
            .addQueryParam("token", properties.getProperty("token"))
            .addHeader("Content-type", CONTENT_TYPE)
            .addFilter(new ResponseLoggingFilter())
            .addFilter(new RequestLoggingFilter())
            .build();
    }

    public Response makeRequest(Method method, String endpoint) {
        return given(REQUEST_SPECIFICATION)
            .request(method, endpoint)
            .then()
            .extract()
            .response();
    }

    public Response makeRequest(Method method, String endpoint, Map<String, String> params) {
        return given(REQUEST_SPECIFICATION)
            .queryParams(params)
            .request(method, endpoint)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .response();
    }

    public Response makeRequest(Method method, String endpoint, String bodyParams) {
        return given(REQUEST_SPECIFICATION)
            .body(bodyParams)
            .request(method, endpoint)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .response();
    }
}
