package com.epam.tc.hw3;

import static com.epam.tc.hw3.utils.Constants.DEFAULT_BOARD_NAME;
import static com.epam.tc.hw3.utils.Constants.DEFAULT_CARD_NAME;
import static com.epam.tc.hw3.utils.Constants.DEFAULT_LIST_NAME;

import com.epam.tc.hw3.data.DataProviderForTest;
import com.epam.tc.hw3.dto.BoardDto;
import com.epam.tc.hw3.dto.CardDto;
import com.epam.tc.hw3.dto.LabelDto;
import com.epam.tc.hw3.dto.ListDto;
import com.epam.tc.hw3.service.assertions.LabelAssertions;
import com.epam.tc.hw3.service.steps.BoardSteps;
import com.epam.tc.hw3.service.steps.CardSteps;
import com.epam.tc.hw3.service.steps.LabelSteps;
import com.epam.tc.hw3.service.steps.ListSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LabelTest {

    private BoardSteps boardAction;
    private LabelSteps labelAction;
    private LabelAssertions labelAssertions;
    private BoardDto board;
    private CardDto card;
    private String idLabel;

    @BeforeMethod
    public void setup() {
        boardAction = new BoardSteps();
        ListSteps listAction = new ListSteps();
        CardSteps cardAction = new CardSteps();
        labelAction = new LabelSteps();
        labelAssertions = new LabelAssertions();
        board = boardAction.createBoard(DEFAULT_BOARD_NAME);
        ListDto list = listAction.createList(DEFAULT_LIST_NAME, board);
        card = cardAction.createCard(DEFAULT_CARD_NAME, list);
    }

    @AfterMethod
    public void teardown() {
        boardAction.deleteBoard(board.getId());
    }

    @Test(dataProvider = "testDataForLabel",
          dataProviderClass = DataProviderForTest.class,
          description = "Create a label in card and get it test")
    public void createCardTest(String name, String color) {
        LabelDto label = labelAction.createLabel(name, color, card);
        idLabel = label.getId();

        Response labelResponse = labelAction.getLabel(idLabel);
        labelAssertions.checkName(label, name)
                       .checkId(label, idLabel)
                       .checkColor(label, color)
                       .checkStatus(labelResponse, HttpStatus.SC_OK);
    }

    @Test(dataProvider = "testDataForLabel",
          dataProviderClass = DataProviderForTest.class,
          description = "Delete a label and check its status, and get deleted label")
    public void deleteAndGetLabelTest(String name, String color) {

        LabelDto label = labelAction.createLabel(name, color, card);
        idLabel = label.getId();

        Response deleteLabelResponse = labelAction.removeLabel(idLabel);
        labelAssertions.checkDeletedLabel(deleteLabelResponse)
                       .checkStatus(deleteLabelResponse, HttpStatus.SC_OK);

        Response getDeletedLabelResponse = labelAction.getLabel(idLabel);
        labelAssertions.checkStatus(getDeletedLabelResponse, HttpStatus.SC_NOT_FOUND);
    }
}
