package com.management.oop.test.utils;

import java.util.ArrayList;
import java.util.List;

public class TaskBaseConstants {
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int BOARD_NAME_MIN_LENGTH = 5;
    public static final int TEAM_NAME_MIN_LENGTH = 5;
    public static final int TEAM_NAME_MAX_LENGTH = 15;
    public static final int VALID_RATING = 20;
    public static final int VALID_ID = 1;
    public static final int INVALID_RATING = 101;
    public static final String VALID_PERSON_NAME = "person name";
    public static final String INVALID_PERSON_NAME = "invalid name";

    public static final String VALID_TITLE = TestUtilities.getString(TITLE_MIN_LENGTH + 1);
    public static final String VALID_TITLE_1 = TestUtilities.getString(TITLE_MIN_LENGTH + 2);

    public static final String INVALID_TITLE = TestUtilities.getString(TITLE_MIN_LENGTH - 1);

    public static final String VALID_BOARD_NAME = TestUtilities.getString(BOARD_NAME_MIN_LENGTH + 1);
    public static final String INVALID_BOARD_NAME = TestUtilities.getString(BOARD_NAME_MIN_LENGTH - 1);

    public static final String VALID_TEAM_NAME = TestUtilities.getString(TEAM_NAME_MIN_LENGTH + 1);
    public static final String INVALID_TEAM_NAME = TestUtilities.getString(TEAM_NAME_MAX_LENGTH - 1);
    public static final String VALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_MIN_LENGTH + 1);
    public static final String INVALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_MIN_LENGTH - 1);
    public static final List<String> STEPS = new ArrayList<>();
}
