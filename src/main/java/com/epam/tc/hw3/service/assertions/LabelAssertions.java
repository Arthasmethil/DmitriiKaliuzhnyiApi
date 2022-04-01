package com.epam.tc.hw3.service.assertions;

import static org.testng.Assert.assertEquals;

import com.epam.tc.hw3.dto.LabelDto;
import io.restassured.response.Response;

public class LabelAssertions extends CommonAssertions {

    public LabelAssertions checkName(LabelDto labelDto, String expectedLabelName) {
        assertEquals(labelDto.getName(), expectedLabelName);
        return this;
    }

    public LabelAssertions checkId(LabelDto labelDto, String expectedLabelId) {
        assertEquals(labelDto.getId(), expectedLabelId);
        return this;
    }

    public LabelAssertions checkColor(LabelDto labelDto, String expectedLabelColor) {
        assertEquals(labelDto.getColor(), expectedLabelColor);
        return this;
    }

    public LabelAssertions checkDeletedLabel(Response deleteLabel) {
        checkBodyIsNull(deleteLabel);
        return this;
    }
}
