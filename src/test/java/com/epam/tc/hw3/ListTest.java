package com.epam.tc.hw3;

import static com.epam.tc.hw3.utils.Constants.ARCHIVE_LIST;
import static com.epam.tc.hw3.utils.Constants.DEFAULT_BOARD_NAME;

import com.epam.tc.hw3.data.DataProviderForTest;
import com.epam.tc.hw3.dto.BoardDto;
import com.epam.tc.hw3.dto.ListDto;
import com.epam.tc.hw3.service.assertions.ListAssertions;
import com.epam.tc.hw3.service.steps.BoardSteps;
import com.epam.tc.hw3.service.steps.ListSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListTest {

    BoardSteps boardAction;
    ListAssertions listAssertions;
    ListSteps listAction;
    BoardDto board;
    String idList;

    @BeforeMethod
    public void setup() {
        boardAction = new BoardSteps();
        listAssertions = new ListAssertions();
        listAction = new ListSteps();
        board = boardAction.createBoard(DEFAULT_BOARD_NAME);
    }

    @AfterMethod
    public void teardown() {
        boardAction.deleteBoard(board.getId());
    }

    @Test(dataProvider = "testDataForList",
          dataProviderClass = DataProviderForTest.class,
          description = "Create a list test")
    public void createListTest(String name) {

        ListDto listDto = listAction.createList(name, board);
        idList = listDto.getId();

        listAssertions.checkName(listDto, name)
                      .checkId(listDto, idList)
                      .checkStatus(listAction.getList(idList), HttpStatus.SC_OK);
    }

    @Test(dataProvider = "testDataForList",
          dataProviderClass = DataProviderForTest.class,
          description = "Close a list and check its status")
    public void closeListTest(String name) {

        ListDto listDto = listAction.createList(name, board);
        idList = listDto.getId();
        Response closeListResponse = listAction.closeOrOpenList(listDto.getId(), ARCHIVE_LIST);

        listAssertions.checkName(listDto, name)
                      .checkId(listDto, idList)
                      .checkClosedList(closeListResponse)
                      .checkStatus(closeListResponse, HttpStatus.SC_OK);
    }

    @Test(dataProvider = "testDataForOpenList",
          dataProviderClass = DataProviderForTest.class,
          description = "Close a list and check its status, then open list and check its status")
    public void openListTest(String name, String openStatus) {
        ListDto listDto = listAction.createList(name, board);
        idList = listDto.getId();

        Response closeListResponse = listAction.closeOrOpenList(listDto.getId(), ARCHIVE_LIST);
        listAssertions.checkClosedList(closeListResponse);

        Response openListResponse = listAction.closeOrOpenList(idList, openStatus);
        listAssertions.checkOpenedList(openListResponse)
                      .checkStatus(listAction.getList(idList), HttpStatus.SC_OK);
    }
}
