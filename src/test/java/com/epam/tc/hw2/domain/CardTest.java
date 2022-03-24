package com.epam.tc.hw2.domain;

import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.BOARDS_END_POINT;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.BOARDS_END_POINT_BY_ID;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.CARDS_END_POINT;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.CARDS_END_POINT_BY_ID;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.CARD_COLOR;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.DEFAULT_BOARD_NAME;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.DEFAULT_CARD_NAME;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.DEFAULT_LIST_NAME;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.LABEL_CARD_END_POINT_BY_ID;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.LABEL_COLOR;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.LABEL_FOR_CARD_END_POINT;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.LABEL_NAME;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.LIST_END_POINT;
import static com.epam.tc.hw2.data.and.support.EndPointsAndConstants.REMOVE_LABEL_CARD_END_POINT_BY_ID;
import static com.epam.tc.hw2.data.and.support.RequestData.REQUEST_SPECIFICATION;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CardTest {
    private String boardId;
    private String listId;

    @BeforeClass
    public void setupClass() {
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

    public String getCardId() {
        return given(REQUEST_SPECIFICATION)
            .queryParam("name", DEFAULT_CARD_NAME)
            .queryParam("idList", listId)
            .post(CARDS_END_POINT)
            .then()
            .extract().path("id");
    }

    @AfterClass
    public void teardown() {
        given(REQUEST_SPECIFICATION).delete(BOARDS_END_POINT_BY_ID, boardId);
    }

    @Test
    public void createCard() {
        String cardId = getCardId();
        given(REQUEST_SPECIFICATION)
            .get(CARDS_END_POINT_BY_ID, cardId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body("id", is(cardId), "name", is(DEFAULT_CARD_NAME));
    }

    @Test
    public void deleteCard() {
        String cardId = getCardId();
        given(REQUEST_SPECIFICATION)
            .when()
            .delete(CARDS_END_POINT_BY_ID, cardId)
            .then()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void updateCard() {
        String cardId = getCardId();
        given(REQUEST_SPECIFICATION)
            .body("{ \"cover\" : {\"color\" : \"" + CARD_COLOR + "\"} }")
            .put(CARDS_END_POINT_BY_ID, cardId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body("id", is(cardId))
            .body("cover.color", is(CARD_COLOR));
    }

    @Test
    public void createLabelCard() {
        String cardId = getCardId();
        String labelId = given(REQUEST_SPECIFICATION)
            .queryParam("name", LABEL_NAME)
            .queryParam("color", LABEL_COLOR)
            .post(LABEL_FOR_CARD_END_POINT, cardId)
            .then()
            .extract().path("id");

        given(REQUEST_SPECIFICATION)
            .get(LABEL_CARD_END_POINT_BY_ID, labelId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body("name", is(LABEL_NAME), "color", is(LABEL_COLOR), "id", is(labelId));
    }

    @Test
    public void deleteLabelCard() {
        String cardId = getCardId();
        String labelId = given(REQUEST_SPECIFICATION)
            .queryParam("color", LABEL_COLOR)
            .post(LABEL_FOR_CARD_END_POINT, cardId)
            .then()
            .extract().path("id");

        given(REQUEST_SPECIFICATION)
            .delete(REMOVE_LABEL_CARD_END_POINT_BY_ID, cardId, labelId)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body("_value", nullValue());
    }
}

