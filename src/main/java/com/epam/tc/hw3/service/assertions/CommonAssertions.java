package com.epam.tc.hw3.service.assertions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import io.restassured.response.Response;

public class CommonAssertions {

    public void checkStatus(Response response, int expectedStatus) {
        assertEquals(response.getStatusCode(), expectedStatus);
    }

    public CommonAssertions checkBodyIsNull(Response response) {
        String value = response.then().extract().response().path("_value");
        assertNull(value);
        return this;
    }
}
