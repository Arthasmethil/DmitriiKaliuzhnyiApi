package com.epam.tc.hw3;

import static com.epam.tc.hw3.utils.Constants.DEFAULT_BOARD_NAME;
import static com.epam.tc.hw3.utils.Constants.DEFAULT_LIST_NAME;

import com.epam.tc.hw3.data.DataProviderForTest;
import com.epam.tc.hw3.dto.BoardDto;
import com.epam.tc.hw3.dto.CardDto;
import com.epam.tc.hw3.dto.ListDto;
import com.epam.tc.hw3.service.assertions.CardAssertions;
import com.epam.tc.hw3.service.steps.BoardSteps;
import com.epam.tc.hw3.service.steps.CardSteps;
import com.epam.tc.hw3.service.steps.ListSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CardTest {

    BoardSteps boardAction;
    ListSteps listAction;
    CardSteps cardAction;
    CardAssertions cardAssertions;
    BoardDto board;
    ListDto list;
    String idCard;

    @BeforeMethod
    public void setup() {
        boardAction = new BoardSteps();
        listAction = new ListSteps();
        cardAction = new CardSteps();
        cardAssertions = new CardAssertions();
        board = boardAction.createBoard(DEFAULT_BOARD_NAME);
        list = listAction.createList(DEFAULT_LIST_NAME,board);
    }

    @AfterMethod
    public void teardown() {
        boardAction.deleteBoard(board.getId());
    }

    @Test(dataProvider = "testDataForCard",
          dataProviderClass = DataProviderForTest.class,
          description = "Create a card in list test")
    public void createCardTest(String name) {

        CardDto card = cardAction.createCard(name, list);
        idCard = card.getId();

        cardAssertions.checkName(card, name)
                      .checkId(card, idCard)
                      .checkStatus(cardAction.getCard(idCard), HttpStatus.SC_OK);
    }

    @Test(dataProvider = "testDataForCardWithColor",
          dataProviderClass = DataProviderForTest.class,
          description = "Close a list and check its status")
    public void changeColorCardTest(String name, String color) {

        CardDto card = cardAction.createCard(name, list);
        idCard = card.getId();

        Response updateCoverCard = cardAction.updateCoverCard(idCard, color);
        cardAssertions.checkName(card, name)
                      .checkId(card, idCard)
                      .checkUpdatedColor(updateCoverCard, color)
                      .checkStatus(updateCoverCard, HttpStatus.SC_OK);
    }
}
