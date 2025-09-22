package ca.bcit.comp2522.bank;

/**
 * This class represents a date.
 * @author Justin Yu
 * @author Tom
 * @version 1.0
 */

public final class Date {

    private static final int DEFAULT_INTEGER_ZERO = 0;
    private static final int TWENTIETH_CENTURY = 2000;
    private static final int NINETEENTH_CENTURY = 1900;
    private static final int EIGHTEENTH_CENTURY = 1800;
    private static final int EXCEPTION_MODIFIER_CONSTANT_ONE = 6;
    private static final int EXCEPTION_MODIFIER_CONSTANT_TWO = 2;
    private static final int CURRENT_YEAR = 2025;
    private static final int MINIMUM_YEAR = 1800;
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;
    private static final int FIRST_DAY_OF_MONTH = 1;
    private static final int LAST_DAY_OF_MONTH_DEFAULT = 31;
    private static final int LAST_DAY_OF_MONTH_THIRTY = 30;
    private static final int LAST_DAY_OF_MONTH_FEBRUARY = 28;
    private static final int LAST_DAY_OF_MONTH_FEBRUARY_LEAP_YEAR = 29;
    private static final int LEAP_YEAR_DIVISIBLE = 4;
    private static final int DIVISIBLE = 0;
    private static final int CENTURY_YEAR = 100;
    private static final int LEAP_YEAR_DIVISIBLE_CENTURY_YEAR = 400;
    private static final int HUNDREDTH_PLACE_DIGIT = 100;
    private static final int DAY_OF_THE_WEEK_CONSTANT_ONE = 12;
    private static final int DAY_OF_THE_WEEK_CONSTANT_TWO = 4;
    private static final int DAY_OF_THE_WEEK_CONSTANT_THREE = 7;
    private static final int BASE_TEN = 10;
    private static final int NTH_DIGIT_CONSTANT = 1;
    private static final long MONTH_CODE = 641630520441L;
    private static final int SATURDAY = 0;
    private static final int SUNDAY = 1;
    private static final int MONDAY = 2;
    private static final int TUESDAY = 3;
    private static final int WEDNESDAY = 4;
    private static final int THURSDAY = 5;
    private static final int FRIDAY = 6;

    private final int year;
    private final int month;
    private final int day;

    /**
     * Constructor for Date class. Initializes instance variables.
     * @param year The numeric value for the year of the date.
     * @param month The numeric value for the month of the date.
     * @param day The numeric value for the day of the date.
     */
    public Date(final int year,
                 final int month,
                 final int day)
    {
        validateDate(year, month, day);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * getter method for instance variable for day.
     * @return the instance variable "day" as an integer.
     */
    public int getDay() {
        return day;
    }

    /**
     * getter method for instance variable for month. Translates from an integer value to the name of the respective month.
     * @return the instance variable "month" as a string.
     */
    public final String getMonth() {
        if (month == JANUARY) {
            return "January";
        } else if (month == FEBRUARY) {
            return "February";
        } else if (month == MARCH) {
            return "March";
        } else if (month == APRIL) {
            return "April";
        } else if (month == MAY) {
            return "May";
        } else if (month == JUNE) {
            return "June";
        } else if (month ==  JULY) {
            return "July";
        } else if (month == AUGUST) {
            return "August";
        } else if (month == SEPTEMBER) {
            return "September";
        } else if (month == OCTOBER) {
            return "October";
        } else if (month == NOVEMBER) {
            return "November";
        } else if (month == DECEMBER) {
            return "December";
        } else {
            throw new IllegalArgumentException("Invalid month");
        }
    }

    /**
     * getter method for instance variable for year.
     * @return the instance variable "year" as an integer.
     */
    public final int getYear() {
        return year;
    }

    /**
     * getter method for the date in the format "YYYY-MM-DD".
     * @return the date in the format of "YYYY-MM-DD" as a string.
     */
    public final String getYYYYMMDD() {
        return String.format("%d-%02d-%02d", year, month, day);
    }

    /**
     * gets the day of the week for the date using a formula.
     * e.g. October 31 1977:
     * step 1: calculate the number of twelves in 77:
     * 6
     * step 2: calculate the remainder from step 1: 77 - 12*6 = 77 - 72 =
     * 5
     * step 3: calculate the number of fours in step 2: 5/4 = 1.25, so
     * 1
     * step 4: add the day of the month to each step above: 31 + 6 + 5 + 1 =
     * 43
     * step 5: add the month code (for jfmamjjasond: 144025036146): for october it is 1: 43 + 1 =
     * 44
     * step 6: add the previous five numbers: (44) and mod by 7: 44%7 = 2 (44/7 = 6 remainder 2)
     * step 7: sat sun mon tue wed thu fri is 0 1 2 3 4 5 6; our 2 means Oct 31 1977 was monday
     * Extra notes:
     * a) for January/February dates in leap years, add 6 at the start
     * b) for all dates in the 2000s, add 6 at the start
     * c) for all dates in the 1800s, add 2 at the start
     * @return the respective day of the week for that date as a string.
     */
    public final String getDayOfTheWeek() {
        final int dayOfTheWeek;
        final int formulaStep1;
        final int formulaStep2;
        final int formulaStep3;
        int exceptionModifier;

        exceptionModifier = DEFAULT_INTEGER_ZERO;
        if (year >= TWENTIETH_CENTURY) {
            exceptionModifier += EXCEPTION_MODIFIER_CONSTANT_ONE;
        } else if (year >= EIGHTEENTH_CENTURY && year < NINETEENTH_CENTURY ) {
            exceptionModifier += EXCEPTION_MODIFIER_CONSTANT_TWO;
        }
        if ((isLeapYear(year) && (month == JANUARY || month == FEBRUARY))) {
            exceptionModifier += EXCEPTION_MODIFIER_CONSTANT_ONE;
        }
        formulaStep1 = (year % HUNDREDTH_PLACE_DIGIT) / DAY_OF_THE_WEEK_CONSTANT_ONE;
        formulaStep2 = (year % HUNDREDTH_PLACE_DIGIT) % DAY_OF_THE_WEEK_CONSTANT_ONE;
        formulaStep3 = formulaStep2 / DAY_OF_THE_WEEK_CONSTANT_TWO;
        dayOfTheWeek = (exceptionModifier + day + formulaStep1 + formulaStep2 + formulaStep3 + (int) ((MONTH_CODE / Math.pow(BASE_TEN, month - NTH_DIGIT_CONSTANT)) % BASE_TEN))
                % DAY_OF_THE_WEEK_CONSTANT_THREE;

        if (dayOfTheWeek == SATURDAY) {
            return "Saturday";
        } else if (dayOfTheWeek == SUNDAY) {
            return "Sunday";
        } else if ( dayOfTheWeek == MONDAY) {
            return "Monday";
        } else if (dayOfTheWeek == TUESDAY) {
            return "Tuesday";
        } else if (dayOfTheWeek == WEDNESDAY) {
            return "Wednesday";
        }  else if (dayOfTheWeek == THURSDAY) {
            return "Thursday";
        }  else if (dayOfTheWeek == FRIDAY) {
            return "Friday";
        } else throw new IllegalArgumentException("Invalid day");
    }

    /**
     * Validates the date values(year, month, day) given in the constructor.
     * @param year the input value for the year for the date.
     * @param month the input value for the month for the date.
     * @param day the input value for the day for the date.
     * @throws IllegalArgumentException if year is not between the minimum year and the current year inclusive,
     * or if month is not between 1 and 12, or if day is not within the number of days in the month, leap year exceptions included.
     */
    private static void validateDate(final int year, final int month, final int day) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        boolean throwError = false;

        // Validate year
        if (year < MINIMUM_YEAR || year > CURRENT_YEAR) {
            throwError = true;
            errorMessageBuilder.append(String.format(
                    "Year given must be between minimum year(%d) and the current year(%d).\n",
                    MINIMUM_YEAR, CURRENT_YEAR));
        }

        // Validate month
        if (month < JANUARY || month > DECEMBER) {
            throwError = true;
            errorMessageBuilder.append(String.format(
                    "Month given must be between January(%d) and December(%d), displayed as an integer.\n",
                    JANUARY, DECEMBER));
        }

        // Validate day based on month
        int maxDay;
        if (month == FEBRUARY) {
            maxDay = isLeapYear(year) ? LAST_DAY_OF_MONTH_FEBRUARY_LEAP_YEAR : LAST_DAY_OF_MONTH_FEBRUARY;
        } else if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
            maxDay = LAST_DAY_OF_MONTH_THIRTY;
        } else {
            maxDay = LAST_DAY_OF_MONTH_DEFAULT;
        }

        if (day < FIRST_DAY_OF_MONTH || day > maxDay) {
            throwError = true;
            if (month == FEBRUARY && isLeapYear(year)) {
                errorMessageBuilder.append(String.format(
                        "Day given must be between start(%d) and end of the month(%d). Year is a leap year.\n",
                        FIRST_DAY_OF_MONTH, LAST_DAY_OF_MONTH_FEBRUARY_LEAP_YEAR));
            } else {
                errorMessageBuilder.append(String.format(
                        "Day given must be between start(%d) and end of the month(%d).\n",
                        FIRST_DAY_OF_MONTH, maxDay));
            }
        }
        // Throw exception if any errors were found
        if (throwError) {
            errorMessageBuilder.append(String.format(
                    "Date given was: %d, %02d, %02d (Year, Month, Day)", year, month, day));
            throw new IllegalArgumentException(errorMessageBuilder.toString());
        }
}

    /**
     * Checks if the year is a leap year.
     * @param year the year to check for being a leap year.
     * @return whether the year is a leap year or not as a boolean.
     */
    private static boolean isLeapYear(final int year) {
        return year % LEAP_YEAR_DIVISIBLE == DIVISIBLE && (!(year % CENTURY_YEAR == DIVISIBLE)
                || (year % LEAP_YEAR_DIVISIBLE_CENTURY_YEAR == DIVISIBLE));
    }
}
