package ca.bcit.comp2522.bank;

/**
 * Represents a bank client with personal info and signup details.
 * Stores the client's name, birth/death dates, signup date, and ID.
 * Can return client details as a string.
 *
 * @author Tom Padilla
 * @author Justin Yu
 * @version 2025
 */
public class BankClient {

    private final Name name;
    private final Date birthDate;
    private final Date deathDate;
    private final Date signupDate;
    private final String clientId;

    /**
     * Makes a new BankClient object.
     *
     * @param name the client's name
     * @param birthDate the client's birthdate
     * @param deathDate the client's death date (null if still alive)
     * @param signupDate the date the client joined the bank
     * @param clientId the client's unique ID (6 or 7 characters)
     */
    public BankClient(final Name name, final Date birthDate, final Date deathDate, final Date signupDate, final String clientId)
    {
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.signupDate = signupDate;
        this.clientId = clientId;

    }

    public BankClient(final Name name, final Date birthDate, final Date signupDate, final String clientId) {
        this.name = name;
        this.birthDate = birthDate;
        this.clientId = clientId;
        this.deathDate = null;
        this.signupDate = signupDate;
    }

    /**
     * Gets the client's name.
     *
     * @return the Name object
     */
    public Name getName() {
        return name;
    }

    /**
     * Gets the client's birthdate.
     *
     * @return the birth Date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Gets the client's ID.
     *
     * @return the client ID string
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Returns a string with client details.
     * Includes the name, client ID, alive/dead status, and signup info.
     *
     * @return formatted string with client information
     */
    public String getDetails() {

        if (deathDate == null) {
            return String.format ("%s client #%s (alive) joined on %s-%s %s, %s.",
                    name.getFullName(),
                    clientId,
                    signupDate.getDayOfTheWeek(),
                    signupDate.getMonth(),
                    signupDate.getDay(),
                    signupDate.getYear());
        } else {
            return String.format("%s client #%s (died on %s-%s %s, %s) joined on %s-%s %s, %s.",
                    name.getFullName(),
                    clientId,
                    deathDate.getDayOfTheWeek(),
                    deathDate.getMonth(),
                    deathDate.getDay(),
                    deathDate.getYear(),
                    signupDate.getDayOfTheWeek(),
                    signupDate.getMonth(),
                    signupDate.getDay(),
                    signupDate.getYear());
        }
    }
}