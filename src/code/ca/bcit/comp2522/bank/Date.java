package ca.bcit.comp2522.bank;

public class Date {

    private static final int DEFAULT_INTEGER = 0;
    private static final int TWENTIETH_CENTURY = 2000;
    private static final int NINETEENTH_CENTURY = 1900;
    private static final int EIGHTEENTH_CENTURY = 1800;
    private static final int MODIFIER_CONSTANT_ONE = 6;
    private static final int MODIFIER_CONSTANT_TWO = 2;
    private static final int CURRENT_YEAR = 2025;
    private static final int MINIMUM_YEAR = 1800;
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int APRIL = 4;
    private static final int JUNE = 6;
    private static final int SEPTEMBER = 9;
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
    private static final int LEAP_YEAR_DIVISIBLE_CENTURY = 400;
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

    public Date( final int year, final int month, final int day) {
        validateDate(year, month, day);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getYYYYMMDD() {
        return String.format("%d-%02d-%02d", year, month, day);
    }

    public String getDayOfTheWeek() {
        final int temp;
        final int step1;
        final int step2;
        final int step3;
        int modifier;

        modifier = DEFAULT_INTEGER;
        if (year >= TWENTIETH_CENTURY) {
            modifier += MODIFIER_CONSTANT_ONE;
        } else if (year >= EIGHTEENTH_CENTURY && year < NINETEENTH_CENTURY ) {
            modifier += MODIFIER_CONSTANT_TWO;
        }
        if ((isLeapYear(year, month) && (month == JANUARY || month == FEBRUARY))) {
            modifier += MODIFIER_CONSTANT_ONE;
        }
        step1 = (year % HUNDREDTH_PLACE_DIGIT) / DAY_OF_THE_WEEK_CONSTANT_ONE;
        step2 = (year % HUNDREDTH_PLACE_DIGIT) % DAY_OF_THE_WEEK_CONSTANT_ONE;
        step3 = step2 / DAY_OF_THE_WEEK_CONSTANT_TWO;
        temp = (modifier + day + step1 + step2 + step3 + (int) ((MONTH_CODE / Math.pow(BASE_TEN, month - NTH_DIGIT_CONSTANT)) % BASE_TEN))
                % DAY_OF_THE_WEEK_CONSTANT_THREE;
        return switch(temp) {
            case MONDAY -> "monday";
            case TUESDAY -> "tuesday";
            case WEDNESDAY -> "wednesday";
            case THURSDAY -> "thursday";
            case FRIDAY -> "friday";
            case SATURDAY -> "saturday";
            case SUNDAY -> "sunday";
            default -> "error";
        };
    }

    /**
     *
     * @param year
     * @param month
     * @param day
     * @return
     * @throws
     */
    private static void validateDate(final int year, final int month, final int day) {
        final String errorMessage;
        final StringBuilder errorMessageBuilder;
        errorMessageBuilder = new StringBuilder();
        boolean throwError = false;
        if (year < MINIMUM_YEAR || year > CURRENT_YEAR) {
            throwError = true;
            errorMessageBuilder.append(String.format("Year given must be between minimum year(%d) and the current year(%d).\n", MINIMUM_YEAR, CURRENT_YEAR));
        }
        if (month < JANUARY || month > DECEMBER) {
            throwError = true;
            errorMessageBuilder.append(String.format("Month given must be between January(%d) and December(%d), displayed as an integer.\n", JANUARY, DECEMBER));
        }
        switch (month) {
            case FEBRUARY:
                if (isLeapYear(year, month)) {
                    if (day < FIRST_DAY_OF_MONTH || day > LAST_DAY_OF_MONTH_FEBRUARY_LEAP_YEAR) {
                        throwError = true;
                        errorMessageBuilder.append(String.format("Day given must be between start(%d) and end of the month(%d). Year is a leap year.\n", FIRST_DAY_OF_MONTH, LAST_DAY_OF_MONTH_FEBRUARY_LEAP_YEAR));
                    }
                } else if (day < FIRST_DAY_OF_MONTH || day > LAST_DAY_OF_MONTH_FEBRUARY) {
                    throwError = true;
                    errorMessageBuilder.append(String.format("Day given must be between start(%d) and end of the month(%d).\n", FIRST_DAY_OF_MONTH, LAST_DAY_OF_MONTH_FEBRUARY));
                }
                break;
            case APRIL:
//                fallthrough
            case JUNE:
//                fallthrough
            case SEPTEMBER:
//                fallthrough
            case NOVEMBER:
                if (day < FIRST_DAY_OF_MONTH || day > LAST_DAY_OF_MONTH_THIRTY) {
                    throwError = true;
                    errorMessageBuilder.append(String.format("Day given must be between start(%d) and end of the month(%d).\n", FIRST_DAY_OF_MONTH, LAST_DAY_OF_MONTH_THIRTY));
                }
                break;
            default:
//                every other month or invalid month
                if (day < FIRST_DAY_OF_MONTH || day > LAST_DAY_OF_MONTH_DEFAULT) {
                    throwError = true;
                    errorMessageBuilder.append(String.format("Day given must be between start(%d) and end of the month(%d).\n", FIRST_DAY_OF_MONTH, LAST_DAY_OF_MONTH_DEFAULT));
                }
                break;
        }
        if (throwError) {
            errorMessageBuilder.append(String.format("Date given was: %d, %02d, %02d (Year, Month, Day)", year, month, day));
            errorMessage = errorMessageBuilder.toString();
            throw new IllegalArgumentException(errorMessage);
        }
    }
    private static boolean isLeapYear(final int year, final int month) {
        return year % LEAP_YEAR_DIVISIBLE == DIVISIBLE && (!(year % CENTURY_YEAR == DIVISIBLE)
                || (year % LEAP_YEAR_DIVISIBLE_CENTURY == DIVISIBLE));
    }

    public static void main(final String[] args) {
        Date d1 = new Date(2025, 02, 5);
        System.out.println(d1.getDayOfTheWeek());
        System.out.println(d1.getYYYYMMDD());
//        delete later
    }
}
