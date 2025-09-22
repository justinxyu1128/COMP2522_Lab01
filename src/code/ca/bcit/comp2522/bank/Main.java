package ca.bcit.comp2522.bank;
/**
 * This class tests several classes: Name, Date, BankClient, BankAccount.
 * @author Justin Yu
 * @author Tom
 * @version 1.0
 */
public final class Main {

    private static final int PIN_ALBERT_EINSTEIN = 3141;
    private static final int PIN_NELSON_MANDELA = 4664;
    private static final int PIN_FRIDA_KAHLO = 1907;
    private static final int PIN_JACKIE_CHAN = 1954;

    private static final double BALANCE_USD_ALBERT_EINSTEIN = 1000.00;
    private static final double BALANCE_USD_NELSON_MANDELA = 2000.00;
    private static final double BALANCE_USD_FRIDA_KAHLO = 500.00;
    private static final double BALANCE_USD_JACKIE_CHAN = 3000.00;

    private static final double WITHDRAWN_USD_ALBERT_EINSTEIN = 100.00;
    private static final double WITHDRAWN_USD_NELSON_MANDELA = 200.00;
    private static final double WITHDRAWN_USD_FRIDA_KAHLO = 50.00;
    private static final double WITHDRAWN_USD_JACKIE_CHAN = 500.00;

    private static final Date SIGNUP_DATE_ALBERT_EINSTEIN = new Date(1900, 1, 1);
    private static final Date SIGNUP_DATE_NELSON_MANDELA = new Date(1994, 5, 10);
    private static final Date SIGNUP_DATE_FRIDA_KAHLO = new Date(1940, 1, 1);
    private static final Date SIGNUP_DATE_JACKIE_CHAN = new Date(1980, 10, 1);

    private static final Date ACCOUNT_OPENED_ALBERT_EINSTEIN = new Date(1900, 1, 1);
    private static final Date ACCOUNT_OPENED_NELSON_MANDELA = new Date(1994, 5, 10);
    private static final Date ACCOUNT_OPENED_FRIDA_KAHLO = new Date(1940, 1, 1);
    private static final Date ACCOUNT_OPENED_JACKIE_CHAN = new Date(1980, 10, 1);

    private static final Date ACCOUNT_CLOSED_ALBERT_EINSTEIN = new Date(1950, 10, 14);
    private static final Date ACCOUNT_CLOSED_FRIDA_KAHLO = new Date(1954, 7, 13);

    private static final Date BIRTH_DATE_ALBERT_EINSTEIN = new Date(1879, 3, 14);
    private static final Date BIRTH_DATE_NELSON_MANDELA = new Date(1918, 7, 18);
    private static final Date BIRTH_DATE_FRIDA_KAHLO = new Date(1907, 7, 6);
    private static final Date BIRTH_DATE_JACKIE_CHAN = new Date(1954, 4, 7);

    private static final Date DEATH_DATE_ALBERT_EINSTEIN = new Date(1955, 4, 18);
    private static final Date DEATH_DATE_NELSON_MANDELA = new Date(2013, 12, 5);
    private static final Date DEATH_DATE_FRIDA_KAHLO = new Date(1954, 7, 13);

    /**
     * Runs the program.
     * @param args unused
     */
    public static void main(final String[] args) {
        final BankAccount bankAccountAE;
        final BankAccount bankAccountNM;
        final BankAccount bankAccountFK;
        final BankAccount bankAccountJC;

        final Name albertE;
        final Name nelsonM;
        final Name fridaK;
        final Name jackieC;

        final String clientIdAE;
        final String clientIdNM;
        final String clientIdFK;
        final String clientIdJC;


        final BankClient clientAE;
        final BankClient clientNM;
        final BankClient clientFK;
        final BankClient clientJC;

        albertE = new Name("Albert", "Einstein");
        nelsonM = new Name("Nelson", "Mandela");
        fridaK = new Name("Frida", "Kahlo");
        jackieC = new Name("Jackie", "Chan");

        clientIdAE = "aaaaaa";
        clientIdNM = "bbbbbb";
        clientIdFK = "cccccc";
        clientIdJC = "dddddd";

        clientAE = new BankClient(albertE,
                BIRTH_DATE_ALBERT_EINSTEIN,
                DEATH_DATE_ALBERT_EINSTEIN,
                SIGNUP_DATE_ALBERT_EINSTEIN,
                clientIdAE
        );

        clientNM = new BankClient(nelsonM,
                BIRTH_DATE_NELSON_MANDELA,
                DEATH_DATE_NELSON_MANDELA,
                SIGNUP_DATE_NELSON_MANDELA,
                clientIdNM
        );
        clientFK = new BankClient(fridaK,
                BIRTH_DATE_FRIDA_KAHLO,
                DEATH_DATE_FRIDA_KAHLO,
                SIGNUP_DATE_FRIDA_KAHLO,
                clientIdFK
        );
        clientJC = new BankClient(jackieC,
                BIRTH_DATE_JACKIE_CHAN,
                SIGNUP_DATE_JACKIE_CHAN,
                clientIdJC
        );

        bankAccountAE = new BankAccount("abc123",
                BALANCE_USD_ALBERT_EINSTEIN,
                ACCOUNT_OPENED_ALBERT_EINSTEIN,
                ACCOUNT_CLOSED_ALBERT_EINSTEIN,
                PIN_ALBERT_EINSTEIN,
                clientAE
        );
        System.out.println("Initials: " + bankAccountAE.getClient().getName().getInitials());
        System.out.println("Full name: " + bankAccountAE.getClient().getName().getFullName());
        System.out.println("Reversed name: " + bankAccountAE.getClient().getName().getReverseName());
        System.out.println("Client details: " + bankAccountAE.getClient().getDetails());
        System.out.println("Account details: " + bankAccountAE.getDetails());
        System.out.println("Withdrawing $USD: " + WITHDRAWN_USD_ALBERT_EINSTEIN);
        bankAccountAE.withdraw(WITHDRAWN_USD_ALBERT_EINSTEIN, PIN_ALBERT_EINSTEIN);
        System.out.println("New balance in USD: " + bankAccountAE.getBalanceUsd());
        System.out.println();

        bankAccountNM = new BankAccount("654321",
                BALANCE_USD_NELSON_MANDELA,
                ACCOUNT_OPENED_NELSON_MANDELA,
                PIN_NELSON_MANDELA,
                clientNM
        );
        System.out.println("Initials: " + bankAccountNM.getClient().getName().getInitials());
        System.out.println("Full name: " + bankAccountNM.getClient().getName().getFullName());
        System.out.println("Reversed name: " + bankAccountNM.getClient().getName().getReverseName());
        System.out.println("Client details: " + bankAccountNM.getClient().getDetails());
        System.out.println("Account details: " + bankAccountNM.getDetails());
        System.out.println("Withdrawing $USD: " + WITHDRAWN_USD_NELSON_MANDELA);
        bankAccountNM.withdraw(WITHDRAWN_USD_NELSON_MANDELA, PIN_NELSON_MANDELA);
        System.out.println("New balance in USD: " + bankAccountNM.getBalanceUsd());
        System.out.println();

        bankAccountFK = new BankAccount("frd123",
                BALANCE_USD_FRIDA_KAHLO,
                ACCOUNT_OPENED_FRIDA_KAHLO,
                ACCOUNT_CLOSED_FRIDA_KAHLO,
                PIN_FRIDA_KAHLO,
                clientFK
        );
        System.out.println("Initials: " + bankAccountFK.getClient().getName().getInitials());
        System.out.println("Full name: " + bankAccountFK.getClient().getName().getFullName());
        System.out.println("Reversed name: " + bankAccountFK.getClient().getName().getReverseName());
        System.out.println("Client details: " + bankAccountFK.getClient().getDetails());
        System.out.println("Account details: " + bankAccountFK.getDetails());
        System.out.println("Withdrawing $USD: " + WITHDRAWN_USD_FRIDA_KAHLO);
        bankAccountNM.withdraw(WITHDRAWN_USD_FRIDA_KAHLO, PIN_FRIDA_KAHLO);
        System.out.println("New balance in USD: " + bankAccountFK.getBalanceUsd());
        System.out.println();

        bankAccountJC = new BankAccount("chan789",
                BALANCE_USD_JACKIE_CHAN,
                ACCOUNT_OPENED_JACKIE_CHAN,
                PIN_JACKIE_CHAN,
                clientJC
        );
        System.out.println("Initials: " + bankAccountJC.getClient().getName().getInitials());
        System.out.println("Full name: " + bankAccountJC.getClient().getName().getFullName());
        System.out.println("Reversed name: " + bankAccountJC.getClient().getName().getReverseName());
        System.out.println("Client details: " + bankAccountJC.getClient().getDetails());
        System.out.println("Account details: " + bankAccountJC.getDetails());
        System.out.println("Withdrawing $USD: " + WITHDRAWN_USD_JACKIE_CHAN);
        bankAccountNM.withdraw(WITHDRAWN_USD_JACKIE_CHAN, PIN_JACKIE_CHAN);
        System.out.println("New balance in USD: " + bankAccountJC.getBalanceUsd());
    }
}
