package com.epam.tc.hw3.service.steps;

import static com.epam.tc.hw3.utils.Endpoints.CARDS_END_POINT;
import static com.epam.tc.hw3.utils.Endpoints.CARDS_END_POINT_BY_ID;

import com.epam.tc.hw3.dto.CardDto;
import com.epam.tc.hw3.dto.ListDto;
import com.epam.tc.hw3.service.CommonService;
import com.google.gson.Gson;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class CardSteps extends CommonService {

    public CardDto createCard (String name, ListDto listDto) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("idList", listDto.getId());
        return
            new Gson().fromJson(
                new CommonService()
                    .makeRequest(Method.POST, CARDS_END_POINT, params)
                    .getBody().asString(), CardDto.class);
    }

    public Response getCard(String id) {
        return makeRequest(Method.GET, String.format(CARDS_END_POINT_BY_ID, id));
    }

    public Response updateCoverCard(String id, String color) {
        String colorValue = "{ \"cover\" : {\"color\" : \"" + color + "\"} }";
        return makeRequest(Method.PUT, String.format(CARDS_END_POINT_BY_ID, id), colorValue);
    }

}
