package ca.bcit.comp2522.bank;

/**
 * Name class to represent a person's name with a first and last name.
 * Checks for blank names, "admin", or names that are too long.
 *
 * @author Tom Padilla
 * @author Justin Yu
 * @version 2025
 */
public class Name {

//    Constant for maximum length of a name
    private static final int MAX_LENGTH = 44;

//    Constant for name left blank
    private static final String SPACE = " ";

//    constant for String "admin"
    private static final String ADMIN_FORB = "admin";

    private final String firstName;
    private final String lastName;

    /**
     * Makes a new name object
     *
     * @param firstName the first name,
     * @param lastName  the last name,
     * @throws IllegalArgumentException if names are = blank, admin, or are too long.
     */
    public Name(final String firstName,
                String lastName
    )
    {
        this.firstName = firstName;
        this.lastName = lastName;

        if (firstName.equals(SPACE) || lastName.equals(SPACE)) {
            throw new IllegalArgumentException("Name cannot be blank");
        }

        if (firstName.equals(ADMIN_FORB) || lastName.equals(ADMIN_FORB)) {
            throw new IllegalArgumentException("Name cannot be admin");
        }

        if (firstName.length() + lastName.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Name too long");
        }

    }

    /**
     * Gets initials of the person in uppercase.
     *
     * @return initials like A.E
     */
    public String getInitials() {

        return (String.valueOf(firstName.charAt(0)) +
                lastName.charAt(0)).toUpperCase();
    }

    /**
     * Gets the full name with the first letter capitalized.
     *
     * @return the full name
     */
    public String getFullName() {
        return Character.toUpperCase(firstName.charAt(0)) +
                firstName.substring(1).toLowerCase() +
                " " +
                Character.toUpperCase(lastName.charAt(0)) +
                lastName.substring(1).toLowerCase();
    }

    /**
     * Gets the reversed version of the name.
     *
     * @return the reversed named string.
     */
    public String getReverseName() {

        return new StringBuilder(firstName + " " +
                lastName).reverse().toString();
    }
}
