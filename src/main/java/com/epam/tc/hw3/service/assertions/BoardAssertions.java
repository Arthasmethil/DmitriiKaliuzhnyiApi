package com.epam.tc.hw3.service.assertions;

import static org.testng.Assert.assertEquals;

import com.epam.tc.hw3.dto.BoardDto;
import io.restassured.response.Response;

public class BoardAssertions extends CommonAssertions {

    public BoardAssertions checkName(BoardDto board, String expectedBoardName) {
        assertEquals(board.getName(), expectedBoardName);
        return this;
    }

    public BoardAssertions checkId(BoardDto board, String expectedBoardId) {
        assertEquals(board.getId(), expectedBoardId);
        return this;
    }

    public CommonAssertions checkDeletedBoard(Response deleteBoard) {
        return checkBodyIsNull(deleteBoard);
    }
}
