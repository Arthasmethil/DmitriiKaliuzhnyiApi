package com.epam.tc.hw2.domain;

import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.BOARDS_END_POINT;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.BOARDS_END_POINT_BY_ID;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.DEFAULT_BOARD_DESCRIPTION;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.DEFAULT_BOARD_NAME;
import static com.epam.tc.hw2.data.and.support.RequestData.REQUEST_SPECIFICATION;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardTest {
    private String boardId;

    @BeforeMethod
    public void setup() {
        Response boardResponse = given(REQUEST_SPECIFICATION)
            .queryParam("name", DEFAULT_BOARD_NAME)
            .post(BOARDS_END_POINT);
        boardId = boardResponse.then().extract().path("id");
    }

    @AfterMethod
    public void teardown() {
        given(REQUEST_SPECIFICATION).delete(BOARDS_END_POINT_BY_ID, boardId);
    }

    @Test
    public void createBoard() {
        given(REQUEST_SPECIFICATION)
            .get(BOARDS_END_POINT_BY_ID, boardId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body("id", is(boardId), "name", is(DEFAULT_BOARD_NAME));
    }

    @Test
    public void deleteBoardAndGetDeletedBoard() {
        given(REQUEST_SPECIFICATION)
            .delete(BOARDS_END_POINT_BY_ID, boardId)
            .then()
            .body("_value", nullValue());

        given(REQUEST_SPECIFICATION)
            .get(BOARDS_END_POINT_BY_ID, boardId)
            .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void updateBoard() {
        given(REQUEST_SPECIFICATION)
            .queryParam("desc", DEFAULT_BOARD_DESCRIPTION)
            .put(BOARDS_END_POINT_BY_ID, boardId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body("id", is(boardId), "desc", is(DEFAULT_BOARD_DESCRIPTION));
    }
}
