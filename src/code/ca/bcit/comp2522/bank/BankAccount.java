package ca.bcit.comp2522.bank;
/**
 * This class models a bank account.
 * @author Justin Yu
 * @author Tom
 * @version 1.0
 */
public final class BankAccount {

    private static final double MIN_BALANCE_USD = 0.00;
    private static final double DEFAULT_BALANCE_USD = 0.00;
    private static final double MIN_DEPOSIT_OR_WITHDRAW_USD = 0.00;
    private static final int MIN_VALUE_PIN = 0;
    private static final int MAX_VALUE_PIN = 9999;
    private static final int MIN_ACCOUNT_NUMBER_LENGTH = 6;
    private static final int MAX_ACCOUNT_NUMBER_LENGTH = 7;

    private final Date accountOpened;
    private final Date accountClosed;
    private final int pin;
    private final String accountNumber;
    private double balanceUsd;
    private final BankClient client;

    /**
     * Constructor for BankAccount class. Initializes instance variables.
     * @param accountNumber representing the account number of the client as a string of length 6 or 7.
     * @param balanceUsd the numeric value for the current balance of the client.
     * @param accountOpened the date when the account was opened, represented by a Date object.
     * @param accountClosed the date when the account was closed, represented by a Date object.
     * @param pin the numeric value for the PIN of the client. Must be between 0 and 9999 inclusive.
     * @param client the client themselves and their information, represented by a BankClient object.
     */
    public BankAccount (final String accountNumber,
                        final double balanceUsd,
                        final Date accountOpened,
                        final Date accountClosed,
                        final int pin,
                        final BankClient client)
    {
        validateAccountNumber(accountNumber);
        validateBalanceUsd(balanceUsd);
        validatePin(pin);

        this.accountNumber = accountNumber;
        this.balanceUsd = balanceUsd;
        this.pin = pin;
        this.accountOpened = accountOpened;
        this.accountClosed = accountClosed;
        this.client = client;
    }

    /**
     * Overloaded constructor for BankAccount class. Initializes instance variables.
     * Replaces "accountClosed" Date with null if the account was never closed.
     * @param accountNumber representing the account number of the client as a string of length 6 or 7.
     * @param balanceUsd the numeric value for the current balance of the client.
     * @param accountOpened the date when the account was opened, represented by a Date object.
     * @param pin the numeric value for the PIN of the client. Must be between 0 and 9999 inclusive.
     * @param client the client themselves and their information, represented by a BankClient object.
     */
    public BankAccount(final String accountNumber,
                       final double balanceUsd,
                       final Date accountOpened,
                       final int pin,
                       final BankClient client)
    {
        this(accountNumber, balanceUsd, accountOpened, null, pin, client);
    }

    /**
     * Overloaded constructor for BankAccount class. Initializes instance variables.
     * Replaces "balanceUsd" int with default balance of USD$0 if a value is not given.
     * @param accountNumber representing the account number of the client as a string of length 6 or 7.
     * @param accountOpened the date when the account was opened, represented by a Date object.
     * @param pin the numeric value for the PIN of the client. Must be between 0 and 9999 inclusive.
     * @param client the client themselves and their information, represented by a BankClient object.
     */
    public BankAccount(final String accountNumber,
                       final Date accountOpened,
                       final int pin,
                       final BankClient client)
    {
        this(accountNumber, DEFAULT_BALANCE_USD, accountOpened, null, pin, client);
    }

    /**
     * getter method for the client.
     * @return the current client as a BankClient object.
     */
    public final BankClient getClient() {
        return this.client;
    }
    /**
     * getter method for current balance of the client.
     * @return the current balance of the client in a readable string format.
     */
    public final String getBalanceUsd() {
        return String.format("Balance is USD$%.2f", this.balanceUsd);
    }

    /**
     * Deposits USD dollars into the account.
     * @param amountUsd the amount of money to be deposited into the account in USD.
     */
    public final void deposit(final double amountUsd) {
        if(amountUsd > MIN_DEPOSIT_OR_WITHDRAW_USD) {
            this.balanceUsd += amountUsd;
        } else {
            throw new IllegalArgumentException("Amount to deposit is too low: " + amountUsd);
        }
    }

    /**
     * Withdraws USD dollars from the account.
     * @param amountUsd the amount of money to be withdrawn from the account in USD.
     */
    public final void withdraw(final double amountUsd) {
        if(amountUsd > MIN_DEPOSIT_OR_WITHDRAW_USD) {
            if (this.balanceUsd >= amountUsd) {
                this.balanceUsd -= amountUsd;
            } else {
                throw new IllegalArgumentException("Amount to withdraw is too high: " + amountUsd);
            }
        } else {
            throw new IllegalArgumentException("Amount to withdraw is too low: " + amountUsd);
        }
    }

    /**
     * Withdraws USD dollars from the account. Uses a PIN value to authenticate the transaction.
     * @param amountUsd the amount of money to be withdrawn from the account in USD.
     * @param pinToMatch the PIN value to be matched against the account's PIN to be used in authentication.
     */
    public final void withdraw(final double amountUsd, final int pinToMatch) {
        if(pinToMatch == this.pin) {
            if(amountUsd > MIN_DEPOSIT_OR_WITHDRAW_USD) {
                if (this.balanceUsd >= amountUsd) {
                    this.balanceUsd -= amountUsd;
                } else {
                    throw new IllegalArgumentException("Amount to withdraw is too high: " + amountUsd);
                }
            } else {
                throw new IllegalArgumentException("Amount to withdraw is too low: " + amountUsd);
            }
        } else {
            throw new IllegalArgumentException("Wrong PIN: " + pinToMatch);
        }
    }

    /**
     * Returns the details of the account and the client.
     * @return the full name, current balance in USD, account number,
     * date of account opening, and date of account closing as a string.
     */
    public final String getDetails() {
        final String details;
        final StringBuilder detailsBuilder;

        detailsBuilder = new StringBuilder();
        detailsBuilder.append(String.format("%s had $%.2f USD in account #%s which he opened on %s %s %02d, %d",
                this.client.getName().getFullName(),
                this.balanceUsd,
                this.accountNumber,
                this.accountOpened.getDayOfTheWeek(),
                this.accountOpened.getMonth(),
                this.accountOpened.getDay(),
                this.accountOpened.getYear()
        ));
        if (this.accountClosed != null) {
            detailsBuilder.append(String.format(" and closed %s %s %02d, %d.",
                    this.accountClosed.getDayOfTheWeek(),
                    this.accountClosed.getMonth(),
                    this.accountClosed.getDay(),
                    this.accountClosed.getYear()
            ));
        } else {
            detailsBuilder.append(".");
        }
        details = detailsBuilder.toString();
        return details;
    }

    /**
     * Validates the PIN given.
     * @param pin the PIN to be validated as an integer value.
     * @throws IllegalArgumentException if PIN given is not between 0 and 9999 inclusive.
     */
    private void validatePin(int pin) {
        if (pin < MIN_VALUE_PIN || pin > MAX_VALUE_PIN) {
            throw new IllegalArgumentException("Invalid PIN. PIN must be between 0 and 9999 inclusive. PIN entered: " + pin);
        }
    }

    /**
     * Validates the balance given.
     * @param balanceUsd the starting balance of the account as an integer.
     * @throws IllegalArgumentException if balance given is below zero(negative).
     */
    private void validateBalanceUsd(double balanceUsd) {
        if (balanceUsd < MIN_BALANCE_USD) {
            throw new IllegalArgumentException("Balance cannot be negative. Balance entered in USD$" + balanceUsd);
        }
    }

    /**
     * Validates the account number given.
     * @param accountNumber the account number given as a string.
     * @throws IllegalArgumentException if account number contains whitespace or is not a string with length 6 or 7.
     */
    private void validateAccountNumber(String accountNumber) {
        if (accountNumber.contains(" ")) {
            throw new IllegalArgumentException("Account number cannot contain whitespace. Account number entered: " + accountNumber);
        }
        if (accountNumber.length() < MIN_ACCOUNT_NUMBER_LENGTH || accountNumber.length() > MAX_ACCOUNT_NUMBER_LENGTH) {
            throw new IllegalArgumentException("Account number must be either 6 or 7 characters long. Account number: " + accountNumber);
        }
    }
}
