package com.epam.tc.hw2.data.and.support;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class RequestData {
    private static final PropertiesProvider properties = new PropertiesProvider();
    public static final RequestSpecification REQUEST_SPECIFICATION = new RequestSpecBuilder()
        .setBaseUri(properties.getProperty("base.url"))
        .addQueryParam("key", properties.getProperty("key"))
        .addQueryParam("token", properties.getProperty("token"))
        .addHeader("Content-type", "application/json")
        .addFilter(new ResponseLoggingFilter())
        .build();
}
