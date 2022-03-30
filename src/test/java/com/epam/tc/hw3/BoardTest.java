package com.epam.tc.hw3;

import com.epam.tc.hw3.data.DataProviderForTest;
import com.epam.tc.hw3.dto.BoardDto;
import com.epam.tc.hw3.service.assertions.BoardAssertions;
import com.epam.tc.hw3.service.steps.BoardSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardTest {

    private BoardSteps boardAction;
    private BoardAssertions boardAssertions;
    private String idBoard;

    @BeforeMethod
    public void setup() {
        boardAction = new BoardSteps();
        boardAssertions = new BoardAssertions();
    }

    @AfterMethod
    public void teardown() {
        boardAction.deleteBoard(idBoard);
    }

    @Test(dataProvider = "testDataForBoard",
          dataProviderClass = DataProviderForTest.class,
          description = "Create a board test and get it")
    public void createBoardTest(String name) {

        BoardDto board = boardAction.createBoard(name);
        idBoard = board.getId();

        boardAssertions.checkName(board, name)
                       .checkId(board, idBoard)
                       .checkStatus(boardAction.getBoard(board.getId()), HttpStatus.SC_OK);
    }

    @Test(dataProvider = "testDataForBoard",
          dataProviderClass = DataProviderForTest.class,
          description = "Delete a board test and check board is deleted, then get deleted board")
    public void deleteBoardTest(String name) {

        BoardDto board = boardAction.createBoard(name);
        idBoard = board.getId();

        Response deleteBoard = boardAction.deleteBoard(idBoard);
        boardAssertions.checkDeletedBoard(deleteBoard)
                       .checkStatus(deleteBoard, HttpStatus.SC_OK);

        Response getDeletedBoard = boardAction.getBoard(idBoard);
        boardAssertions.checkStatus(getDeletedBoard, HttpStatus.SC_NOT_FOUND);
    }
}
