package com.epam.tc.hw3.service.assertions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epam.tc.hw3.dto.ListDto;
import io.restassured.response.Response;

public class ListAssertions extends CommonAssertions {

    public ListAssertions checkName(ListDto list, String expectedListName) {
        assertEquals(list.getName(), expectedListName);
        return this;
    }

    public ListAssertions checkId(ListDto list, String expectedListId) {
        assertEquals(list.getId(), expectedListId);
        return this;
    }

    public ListAssertions checkClosedList(Response closedList) {
        boolean statusList = closedList.then().extract().jsonPath().getBoolean("closed");
        assertTrue(statusList);
        return this;
    }

    public ListAssertions checkOpenedList(Response openedList) {
        boolean statusList = openedList.then().extract().jsonPath().getBoolean("closed");
        assertFalse(statusList);
        return this;
    }
}
