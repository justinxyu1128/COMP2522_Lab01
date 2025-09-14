package ca.bcit.comp2522.bank;

public class BankAccount {
    final String clientID;
    final Date accountOpened;
    final Date accountClosed;
    final int pin;
    final String accountNumber;
    double balanceUSD;
    final Client BankClient;


    public BankAccount () {

    }

    void deposit(final double amountUSD) {

    }

    void withdraw(final double amountUSD) {

    }

    void withdraw(final double amountUSD, final int pinToMatch) {

    }

    String getDetails() {
        return "info";
    }
}
