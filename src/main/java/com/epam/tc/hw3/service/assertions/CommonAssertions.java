package com.epam.tc.hw3.service.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;

public class CommonAssertions {

    public void checkStatus(Response response, int expectedStatus) {
        assertThat(response.getStatusCode()).as("Response status").isEqualTo(expectedStatus);
    }

    public CommonAssertions checkBodyIsNull(Response response) {
        Object value = response.then().extract().response().path("_value");
        assertThat(value).isNull();
        return this;
    }
}
