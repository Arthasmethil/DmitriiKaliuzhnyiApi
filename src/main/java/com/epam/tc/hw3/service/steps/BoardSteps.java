package com.epam.tc.hw3.service.steps;

import static com.epam.tc.hw3.utils.Endpoints.BOARDS_END_POINT;
import static com.epam.tc.hw3.utils.Endpoints.BOARDS_END_POINT_BY_ID;

import com.epam.tc.hw3.dto.BoardDto;
import com.epam.tc.hw3.service.CommonService;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.Map;
import org.apache.http.HttpStatus;

public class BoardSteps extends CommonService {

    public BoardDto createBoard (String name) {
        return createDtoObject(
            new CommonService()
                .makeRequest(Method.POST, BOARDS_END_POINT, Map.of("name", name))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response(), BoardDto.class);
    }

    public Response getBoard(String id) {
        return makeRequest(Method.GET, String.format(BOARDS_END_POINT_BY_ID, id));
    }

    public Response deleteBoard(String id) {
        return makeRequest(Method.DELETE, String.format(BOARDS_END_POINT_BY_ID, id));
    }

}
