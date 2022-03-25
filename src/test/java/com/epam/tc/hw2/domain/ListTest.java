package com.epam.tc.hw2.domain;

import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.ARCHIVE_LIST;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.BOARDS_END_POINT;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.BOARDS_END_POINT_BY_ID;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.CONTENT_TYPE;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.DEFAULT_BOARD_NAME;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.DEFAULT_LIST_NAME;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.ENCODING_TYPE;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.HEADER_KEY_FOR_TEST_ONE;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.HEADER_KEY_FOR_TEST_TWO;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.HEADER_VALUE_FOR_TEST_TWO;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.HEADER_VALUE_PATTERN_TEST_ONE;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.LIST_END_POINT;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.LIST_END_POINT_BY_ID;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.LIST_END_POINT_ID_CLOSED;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.UPDATED_LIST_NAME;
import static com.epam.tc.hw2.data.and.support.RequestData.REQUEST_SPECIFICATION;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListTest {
    private String boardId;
    private String listId;

    @BeforeMethod
    public void setup() {
        Response boardResponse = given(REQUEST_SPECIFICATION)
            .queryParam("name", DEFAULT_BOARD_NAME)
            .post(BOARDS_END_POINT);
        boardId = boardResponse.then().extract().path("id");

        Response listResponse = given(REQUEST_SPECIFICATION)
            .queryParam("name", DEFAULT_LIST_NAME)
            .queryParam("idBoard", boardId)
            .post(LIST_END_POINT);
        listId = listResponse.then().extract().path("id");
    }

    @AfterMethod
    public void teardown() {
        given(REQUEST_SPECIFICATION).delete(BOARDS_END_POINT_BY_ID, boardId);
    }

    @Test(description = "Create a list")
    public void createList() {
        given(REQUEST_SPECIFICATION)
            .get(LIST_END_POINT_BY_ID, listId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body("id", is(listId), "name", is(DEFAULT_LIST_NAME));
    }

    @Test(description = "Delete a list")
    public void deleteListAndGetDeletedList() {
        given(REQUEST_SPECIFICATION)
            .queryParam("value", ARCHIVE_LIST)
            .put(LIST_END_POINT_ID_CLOSED, listId)
            .then()
            .contentType(CONTENT_TYPE)
            .and()
            .header(HEADER_KEY_FOR_TEST_ONE, matchesPattern(HEADER_VALUE_PATTERN_TEST_ONE));
    }

    @Test(description = "Update a list")
    public void updateList() {
        given(REQUEST_SPECIFICATION)
            .queryParam("name", UPDATED_LIST_NAME)
            .put(LIST_END_POINT_BY_ID, listId)
            .then()
            .contentType(containsString(ENCODING_TYPE))
            .and()
            .header(HEADER_KEY_FOR_TEST_TWO, containsString(HEADER_VALUE_FOR_TEST_TWO));
    }
}
