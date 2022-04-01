package com.epam.tc.hw3.service.steps;

import static com.epam.tc.hw3.utils.Endpoints.LABEL_CARD_END_POINT_BY_ID;
import static com.epam.tc.hw3.utils.Endpoints.LABEL_FOR_CARD_END_POINT;
import static com.epam.tc.hw3.utils.Endpoints.REMOVE_LABEL_CARD_END_POINT_BY_ID;

import com.epam.tc.hw3.dto.CardDto;
import com.epam.tc.hw3.dto.LabelDto;
import com.epam.tc.hw3.service.CommonService;
import com.google.gson.Gson;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class LabelSteps extends CommonService {

    public LabelDto createLabel (String name, String color, CardDto cardDto) {
        String idCard = cardDto.getId();
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("color", color);
        return
            new Gson().fromJson(
                new CommonService()
                    .makeRequest(Method.POST, String.format(LABEL_FOR_CARD_END_POINT, idCard), params)
                    .getBody().asString(), LabelDto.class);
    }

    public Response getLabel(String id) {
        return makeRequest(Method.GET, String.format(LABEL_CARD_END_POINT_BY_ID, id));
    }

    public Response removeLabel(String id) {
        return makeRequest(Method.DELETE, String.format(REMOVE_LABEL_CARD_END_POINT_BY_ID, id));
    }
}
