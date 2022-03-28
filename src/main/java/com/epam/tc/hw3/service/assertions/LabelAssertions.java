package com.epam.tc.hw3.service.assertions;

import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_COLOR;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_ID;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.dto.LabelDto;
import io.restassured.response.Response;

public class LabelAssertions extends CommonAssertions {

    public LabelAssertions checkName(LabelDto labelDto, String expectedLabelName) {
        String labelName = labelDto.getName();
        assertThat(labelName)
            .as(DESCRIPTION_NAME, labelName, expectedLabelName)
            .isEqualTo(expectedLabelName);
        return this;
    }

    public LabelAssertions checkId(LabelDto labelDto, String expectedLabelId) {
        String labelId = labelDto.getId();
        assertThat(labelId)
            .as(DESCRIPTION_ID, labelId, expectedLabelId)
            .isEqualTo(expectedLabelId);
        return this;
    }

    public LabelAssertions checkColor(LabelDto labelDto, String expectedLabelColor) {
        String labelColor = labelDto.getColor();
        assertThat(labelColor)
            .as(DESCRIPTION_COLOR, labelColor, expectedLabelColor)
            .isEqualTo(expectedLabelColor);
        return this;
    }

    public LabelAssertions checkDeletedLabel(Response deleteLabel) {
        Object value = deleteLabel.then().extract().response().path("_value");
        assertThat(value).isNull();
        return this;
    }
}
