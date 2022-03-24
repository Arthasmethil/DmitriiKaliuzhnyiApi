package com.epam.tc.hw2.data.and.support;

public class EndPointsAndConstants {
    public static final String DEFAULT_BOARD_NAME = "board test name";
    public static final String DEFAULT_BOARD_DESCRIPTION = "New description for test";
    public static final String DEFAULT_LIST_NAME = "list test name";
    public static final String UPDATED_LIST_NAME = "New test list name";
    public static final String DEFAULT_CARD_NAME = "Test card name";
    public static final String CARD_COLOR = "pink";
    public static final String LABEL_COLOR = "green";
    public static final String LABEL_NAME = "Label";
    public static final Boolean ARCHIVE_LIST = true;

    public static final String BOARDS_END_POINT = "/1/boards/";
    public static final String BOARDS_END_POINT_BY_ID = "/1/boards/{id}";
    public static final String LIST_END_POINT = "/1/lists/";
    public static final String LIST_END_POINT_BY_ID = "/1/lists/{id}";
    public static final String LIST_END_POINT_ID_CLOSED = "/1/lists/{id}/closed";
    public static final String CARDS_END_POINT = "/1/cards";
    public static final String CARDS_END_POINT_BY_ID = "/1/cards/{id}";
    public static final String LABEL_FOR_CARD_END_POINT = "/1/cards/{id}/labels";
    public static final String REMOVE_LABEL_CARD_END_POINT_BY_ID = "/1/cards/{id}/idLabels/{idLabel}";
    public static final String LABEL_CARD_END_POINT_BY_ID = "/1/labels/{id}";
}
