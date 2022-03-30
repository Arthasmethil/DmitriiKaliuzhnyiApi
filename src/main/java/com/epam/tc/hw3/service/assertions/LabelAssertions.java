package com.epam.tc.hw3.service.assertions;

import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_COLOR;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_ID;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.dto.LabelDto;
import io.restassured.response.Response;

public class LabelAssertions extends CommonAssertions {

    public LabelAssertions checkName(LabelDto labelDto, String expectedLabelName) {
        assertThat(labelDto.getName())
            .as(DESCRIPTION_NAME, labelDto.getName(), expectedLabelName)
            .isEqualTo(expectedLabelName);
        return this;
    }

    public LabelAssertions checkId(LabelDto labelDto, String expectedLabelId) {
        assertThat(labelDto.getId())
            .as(DESCRIPTION_ID, labelDto.getId(), expectedLabelId)
            .isEqualTo(expectedLabelId);
        return this;
    }

    public LabelAssertions checkColor(LabelDto labelDto, String expectedLabelColor) {
        assertThat(labelDto.getColor())
            .as(DESCRIPTION_COLOR, labelDto.getColor(), expectedLabelColor)
            .isEqualTo(expectedLabelColor);
        return this;
    }

    public LabelAssertions checkDeletedLabel(Response deleteLabel) {
        Object value = deleteLabel.then().extract().response().path("_value");
        assertThat(value).isNull();
        return this;
    }
}
