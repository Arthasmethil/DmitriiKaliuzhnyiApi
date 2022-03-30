package com.epam.tc.hw3.service.steps;

import static com.epam.tc.hw3.utils.Endpoints.LIST_END_POINT;
import static com.epam.tc.hw3.utils.Endpoints.LIST_END_POINT_BY_ID;
import static com.epam.tc.hw3.utils.Endpoints.LIST_END_POINT_ID_CLOSED;

import com.epam.tc.hw3.dto.BoardDto;
import com.epam.tc.hw3.dto.ListDto;
import com.epam.tc.hw3.service.CommonService;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;

public class ListSteps extends CommonService {

    public ListDto createList (String name, BoardDto board) {
        return createDtoObject(
            new CommonService()
                .makeRequest(Method.POST, LIST_END_POINT, Map.of("name", name, "idBoard", board.getId()))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response(), ListDto.class);
    }

    public Response getList(String id) {
        return makeRequest(Method.GET, String.format(LIST_END_POINT_BY_ID, id));
    }

    public Response closeOrOpenList(String id, String trueOrFalse) {
        Map<String, String> params = new HashMap<>();
        params.put("value", trueOrFalse);
        return makeRequest(Method.PUT, String.format(LIST_END_POINT_ID_CLOSED, id), params);
    }
}
