package com.epam.tc.hw3.service.steps;

import static com.epam.tc.hw3.utils.Endpoints.LABEL_CARD_END_POINT_BY_ID;
import static com.epam.tc.hw3.utils.Endpoints.LABEL_FOR_CARD_END_POINT;
import static com.epam.tc.hw3.utils.Endpoints.REMOVE_LABEL_CARD_END_POINT_BY_ID;

import com.epam.tc.hw3.dto.CardDto;
import com.epam.tc.hw3.dto.LabelDto;
import com.epam.tc.hw3.service.CommonService;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.Map;
import org.apache.http.HttpStatus;

public class LabelSteps extends CommonService {

    public LabelDto createLabel (String name, String color, CardDto cardDto) {
        Map<String, String> params = Map.of("name", name, "color", color);
        return createDtoObject(
            new CommonService()
                .makeRequest(Method.POST, String.format(LABEL_FOR_CARD_END_POINT, cardDto.getId()), params)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response(), LabelDto.class);
    }

    public Response getLabel(String id) {
        return makeRequest(Method.GET, String.format(LABEL_CARD_END_POINT_BY_ID, id));
    }

    public Response removeLabel(String id) {
        return makeRequest(Method.DELETE, String.format(REMOVE_LABEL_CARD_END_POINT_BY_ID, id));
    }
}
