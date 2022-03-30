package com.epam.tc.hw3.service.assertions;

import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_ID;
import static com.epam.tc.hw3.utils.Constants.DESCRIPTION_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.dto.BoardDto;
import io.restassured.response.Response;

public class BoardAssertions extends CommonAssertions {

    public BoardAssertions checkName(BoardDto board, String expectedBoardName) {
        assertThat(board.getName())
            .as(DESCRIPTION_NAME, board.getName(), expectedBoardName)
            .isEqualTo(expectedBoardName);
        return this;
    }

    public BoardAssertions checkId(BoardDto board, String expectedBoardId) {
        assertThat(board.getId())
            .as(DESCRIPTION_ID, board.getId(), expectedBoardId)
            .isEqualTo(expectedBoardId);
        return this;
    }

    public CommonAssertions checkDeletedBoard(Response deleteBoard) {
        return checkBodyIsNull(deleteBoard);
    }
}
