package com.epam.tc.hw3.service.assertions;

import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_COLOR;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_ID;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.dto.CardDto;
import io.restassured.response.Response;

public class CardAssertions extends CommonAssertions {

    public CardAssertions checkName(CardDto cardDto, String expectedCardName) {
        assertThat(cardDto.getName())
            .as(DESCRIPTION_NAME, cardDto.getName(), expectedCardName)
            .isEqualTo(expectedCardName);
        return this;
    }

    public CardAssertions checkId(CardDto cardDto, String expectedCardId) {
        assertThat(cardDto.getId())
            .as(DESCRIPTION_ID, cardDto.getId(), expectedCardId)
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
