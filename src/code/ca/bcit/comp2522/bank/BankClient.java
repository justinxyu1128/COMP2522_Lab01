package ca.bcit.comp2522.bank;

public class BankClient {
    private final Name name;
    public BankClient(final Name name, final Date birthDate, final Date deathDate) {
        this.name = name;
    }

    public BankClient(final Name name, final Date birthDate) {
        this(name, birthDate, null);
    }

    public BankClient(Name albertE, Date birthDateAE, Date deathDateAE, Date signupDateAE, String clientIdAE) {
    }

    public BankClient(Name jackieC, Date birthDateJC, Date signupDateJC, String clientIdJC) {
    }

    public Name getName() {
        return this.name;
    }
}
