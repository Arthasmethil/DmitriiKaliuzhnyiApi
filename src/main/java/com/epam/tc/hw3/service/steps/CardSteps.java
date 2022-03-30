package com.epam.tc.hw3.service.steps;

import static com.epam.tc.hw3.utils.Endpoints.CARDS_END_POINT;
import static com.epam.tc.hw3.utils.Endpoints.CARDS_END_POINT_BY_ID;

import com.epam.tc.hw3.dto.CardDto;
import com.epam.tc.hw3.dto.ListDto;
import com.epam.tc.hw3.service.CommonService;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.Map;
import org.apache.http.HttpStatus;

public class CardSteps extends CommonService {

    public CardDto createCard (String name, ListDto listDto) {
        return createDtoObject(
            new CommonService()
                .makeRequest(Method.POST, CARDS_END_POINT, Map.of("name", name, "idList", listDto.getId()))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response(), CardDto.class);
    }

    public Response getCard(String id) {
        return makeRequest(Method.GET, String.format(CARDS_END_POINT_BY_ID, id));
    }

    public Response updateCoverCard(String id, String color) {
        String colorValue = "{ \"cover\" : {\"color\" : \"" + color + "\"} }";
        return makeRequest(Method.PUT, String.format(CARDS_END_POINT_BY_ID, id), colorValue);
    }

}
