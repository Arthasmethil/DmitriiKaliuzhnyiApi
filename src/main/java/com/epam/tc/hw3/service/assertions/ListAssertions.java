package com.epam.tc.hw3.service.assertions;

import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_ID;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.dto.ListDto;
import io.restassured.response.Response;

public class ListAssertions extends CommonAssertions {

    public ListAssertions checkName(ListDto list, String expectedListName) {
        assertThat(list.getName())
            .as(DESCRIPTION_NAME, list.getName(), expectedListName)
            .isEqualTo(expectedListName);
        return this;
    }

    public ListAssertions checkId(ListDto list, String expectedListId) {
        assertThat(list.getId())
            .as(DESCRIPTION_ID, list.getId(), expectedListId)
            .isEqualTo(expectedListId);
        return this;
    }

    public ListAssertions checkClosedList(Response closedList) {
        boolean statusList = closedList.then().extract().jsonPath().getBoolean("closed");
        assertThat(statusList).isTrue();
        return this;
    }

    public ListAssertions checkOpenedList(Response openedList) {
        boolean statusList = openedList.then().extract().jsonPath().getBoolean("closed");
        assertThat(statusList).isFalse();
        return this;
    }
}
