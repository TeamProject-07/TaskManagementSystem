package com.management.oop.test.utils;

import java.util.ArrayList;
import java.util.List;

public class TaskBaseConstants {
    public static final String EXPECTED_KEYWORD = "Test Title";

    public static final int TITLE_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int BOARD_NAME_MIN_LENGTH = 5;
    public static final int TEAM_NAME_MIN_LENGTH = 5;
    public static final int TEAM_NAME_MAX_LENGTH = 15;
    public static final int FEEDBACK_NAME_MIN_LENGTH = 10;


    public static final int VALID_RATING = 20;
    public static final String VALID_TITLE = TestUtilities.getString(TITLE_MIN_LENGTH + 1);
    public static final String INVALID_TITLE = TestUtilities.getString(TITLE_MIN_LENGTH - 1);

    public static final String VALID_BOARD_NAME = TestUtilities.getString(BOARD_NAME_MIN_LENGTH + 1);
    public static final String INVALID_BOARD_NAME = TestUtilities.getString(BOARD_NAME_MIN_LENGTH - 1);

    public static final String VALID_TEAM_NAME = TestUtilities.getString(TEAM_NAME_MIN_LENGTH + 1);
    public static final String INVALID_TEAM_NAME = TestUtilities.getString(TEAM_NAME_MAX_LENGTH - 1);
    public static final String VALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_MIN_LENGTH + 1);
    public static final String INVALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_MIN_LENGTH - 1);
    public static final List<String> STEPS = new ArrayList<>();
}
