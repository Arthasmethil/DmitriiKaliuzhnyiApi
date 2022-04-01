package com.epam.tc.hw3.data;

import org.testng.annotations.DataProvider;

public class DataProviderForTest {

    @DataProvider(name = "testDataForBoard")
    public static Object[][] testDataForBoard() {
        return new Object[][] {
            {"Board"},
            {"BoardName"},
            {"OneMoreBoard"},
            {"BoardCreated"},
            {"BoardTest"}
        };
    }

    @DataProvider(name = "testDataForList")
    public static Object[][] testDataForList() {
        return new Object[][] {
            {"List"},
            {"ListName"},
            {"OneMoreList"},
            {"ListCreated"},
            {"ListTest"}
        };
    }

    @DataProvider(name = "testDataForOpenList")
    public static Object[][] testDataForOpenList() {
        return new Object[][] {
            {"ListOpen", "false"}
        };
    }

    @DataProvider(name = "testDataForCard")
    public static Object[][] testDataForCard() {
        return new Object[][] {
            {"Card"},
            {"CardName"},
            {"OneMoreCard"},
            {"CardCreated"},
            {"CardTest"}
        };
    }

    @DataProvider(name = "testDataForCardWithColor")
    public static Object[][] testDataForCardWithColor() {
        return new Object[][] {
            {"Card", "pink"},
            {"CardName", "black"},
            {"OneMoreCard", "lime"},
            {"CardCreated", "yellow"},
            {"CardTest", "red"}
        };
    }

    @DataProvider(name = "testDataForLabel")
    public static Object[][] testDataForLabel() {
        return new Object[][] {
            {"LabelOne", "orange"},
            {"LabelTwo", "sky"},
            {"LabelThree", "blue"},
            {"LabelFour", "purple"},
            {"LabelFive", "green"}
        };
    }
}
