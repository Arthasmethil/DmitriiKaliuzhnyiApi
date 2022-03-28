package com.epam.tc.hw3.service.assertions;

import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_ID;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.dto.ListDto;
import io.restassured.response.Response;

public class ListAssertions extends CommonAssertions {

    public ListAssertions checkName(ListDto list, String expectedListName) {
        String listName = list.getName();
        assertThat(listName)
            .as(DESCRIPTION_NAME, listName, expectedListName)
            .isEqualTo(expectedListName);
        return this;
    }

    public ListAssertions checkId(ListDto list, String expectedListId) {
        String listId = list.getId();
        assertThat(listId)
            .as(DESCRIPTION_ID, listId, expectedListId)
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
