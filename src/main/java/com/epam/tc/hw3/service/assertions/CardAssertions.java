package com.epam.tc.hw3.service.assertions;

import static org.testng.Assert.assertEquals;

import com.epam.tc.hw3.dto.CardDto;
import io.restassured.response.Response;

public class CardAssertions extends CommonAssertions {

    public CardAssertions checkName(CardDto cardDto, String expectedCardName) {
        assertEquals(cardDto.getName(), expectedCardName);
        return this;
    }

    public CardAssertions checkId(CardDto cardDto, String expectedCardId) {
        assertEquals(cardDto.getId(), expectedCardId);
        return this;
    }

    public CardAssertions checkUpdatedColor(Response updatedColorCard, String color) {
        String value = updatedColorCard.then().extract().response().path("cover.color");
        assertEquals(value, color);
        return this;
    }
}
