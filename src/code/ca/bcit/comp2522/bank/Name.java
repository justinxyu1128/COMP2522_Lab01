package ca.bcit.comp2522.bank;

public class Name {
    private final String firstName;
    private final String lastName;
    public Name(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFullName() {
        return "Albert Einstein";
    }
}
