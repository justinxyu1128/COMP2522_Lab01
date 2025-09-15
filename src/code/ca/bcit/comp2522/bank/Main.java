package ca.bcit.comp2522.bank;
/**
 * This class tests several classes: Name, Date, BankClient, BankAccount.
 * @author Justin Yu
 * @author Tom
 * @version 1.0
 */
public final class Main {

    /**
     * drives the program.
     * @param args unused
     */
    public static void main(final String[] args) {
        BankAccount b1;
        BankAccount b2;
        BankAccount b3;
        BankAccount b4;

        b1 = new BankAccount("abc123",
                1000,
                new Date(1900, 1, 1),
                new Date(1950, 10, 14),
                3141,
                new BankClient(new Name("Albert", "Einstein"),
                        new Date(1879, 3, 14),
                        new Date(1955, 4, 18)
                )
        );
        System.out.println(b1.getClient().getName().getInitials());
        System.out.println(b1.getClient().getName().getFullName());
        System.out.println(b1.getClient().getName().getReverseName());
        System.out.println(b1.getClient().getDetails());
        System.out.println(b1.getDetails());
        b1.withdraw(100, 3141);
        System.out.println(b1.getBalanceUsd());

        b2 = new BankAccount("654321",
                2000,
                new Date(1994, 5, 10),
                4664,
                new BankClient(new Name("Nelson", "Mandela"),
                        new Date(1918, 7, 18),
                        new Date(2013, 12, 5)
                )
        );
        System.out.println(b2.getClient().getName().getInitials());
        System.out.println(b2.getClient().getName().getFullName());
        System.out.println(b2.getClient().getName().getReverseName());
        System.out.println(b2.getClient().getDetails());
        System.out.println(b2.getDetails());
        b2.withdraw(200, 4664);
        System.out.println(b2.getBalanceUsd());

        b3 = new BankAccount("frd123",
                500,
                new Date(1940, 1, 1),
                new Date(1954, 7, 13),
                1907,
                new BankClient(new Name("Frida", "Kahlo"),
                        new Date(1907, 7, 6),
                        new Date(1954, 7, 13)
                )
        );
        System.out.println(b3.getClient().getName().getInitials());
        System.out.println(b3.getClient().getName().getFullName());
        System.out.println(b3.getClient().getName().getReverseName());
        System.out.println(b3.getClient().getDetails());
        System.out.println(b3.getDetails());
        b3.withdraw(50, 1907);
        System.out.println(b3.getBalanceUsd());

        b4 = new BankAccount("chan789",
                3000,
                new Date(1980, 10, 1),
                1954,
                new BankClient(new Name("Jackie", "Chan"),
                        new Date(1954, 4, 7)
                )
        );
        System.out.println(b4.getClient().getName().getInitials());
        System.out.println(b4.getClient().getName().getFullName());
        System.out.println(b4.getClient().getName().getReverseName());
        System.out.println(b4.getClient().getDetails());
        System.out.println(b4.getDetails());
        b4.withdraw(500, 1954);
        System.out.println(b4.getBalanceUsd());
    }
}
