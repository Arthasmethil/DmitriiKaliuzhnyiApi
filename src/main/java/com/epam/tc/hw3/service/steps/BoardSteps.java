package com.epam.tc.hw3.service.steps;

import static com.epam.tc.hw3.utils.Endpoints.BOARDS_END_POINT;
import static com.epam.tc.hw3.utils.Endpoints.BOARDS_END_POINT_BY_ID;

import com.epam.tc.hw3.dto.BoardDto;
import com.epam.tc.hw3.service.CommonService;
import com.google.gson.Gson;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class BoardSteps extends CommonService {

    public BoardDto createBoard (String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        return new Gson().fromJson(
                    new CommonService()
                        .makeRequest(Method.POST, BOARDS_END_POINT, params)
                        .getBody().asString(), BoardDto.class);
    }

    public Response getBoard(String id) {
        return makeRequest(Method.GET, String.format(BOARDS_END_POINT_BY_ID, id));
    }

    public Response deleteBoard(String id) {
        return makeRequest(Method.DELETE, String.format(BOARDS_END_POINT_BY_ID, id));
    }
}
