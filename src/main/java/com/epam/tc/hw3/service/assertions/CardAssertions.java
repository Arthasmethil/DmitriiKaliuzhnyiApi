package com.epam.tc.hw3.service.assertions;

import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_COLOR;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_ID;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.dto.CardDto;
import io.restassured.response.Response;

public class CardAssertions extends CommonAssertions {

    public CardAssertions checkName(CardDto cardDto, String expectedCardName) {
        String cardName = cardDto.getName();
        assertThat(cardName)
            .as(DESCRIPTION_NAME, cardName, expectedCardName)
            .isEqualTo(expectedCardName);
        return this;
    }

    public CardAssertions checkId(CardDto cardDto, String expectedCardId) {
        String cardId = cardDto.getId();
        assertThat(cardId)
            .as(DESCRIPTION_ID, cardId, expectedCardId)
            .isEqualTo(expectedCardId);
        return this;
    }

    public CardAssertions checkUpdatedColor(Response updatedColorCard, String color) {
        Object value = updatedColorCard.then().extract().response().path("cover.color");
        assertThat(value)
            .as(DESCRIPTION_COLOR, value, color)
            .isEqualTo(color);
        return this;
    }
}
