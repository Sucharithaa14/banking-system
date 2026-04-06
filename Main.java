import java.util.Scanner;

public class Main {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to Banking System ===");
        boolean running = true;
        while (running) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> createAccount();
                case 2 -> loginMenu();
                case 3 -> { running = false;
                    System.out.println("Goodbye!"); }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Set password: ");
        String password = scanner.nextLine();
        System.out.print("Initial deposit amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        bank.createAccount(name, password, amount);
    }

    private static void loginMenu() {
        try {
            System.out.print("Enter Account ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            Account account = bank.login(id, password);
            System.out.println("Login successful! Welcome, "
                    + account.getHolderName());
            accountMenu(account);
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accountMenu(Account account) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Logout");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> System.out.println("Balance: Rs."
                        + account.getBalance());
                case 2 -> {
                    System.out.print("Deposit amount: ");
                    double amt = Double.parseDouble(scanner.nextLine());
                    account.deposit(amt);
                    System.out.println("Deposited successfully!");
                }
                case 3 -> {
                    try {
                        System.out.print("Withdraw amount: ");
                        double amt = Double.parseDouble(scanner.nextLine());
                        account.withdraw(amt);
                        System.out.println("Withdrawn successfully!");
                    } catch (InsufficientFundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        System.out.print("Recipient Account ID: ");
                        String toId = scanner.nextLine();
                        System.out.print("Transfer amount: ");
                        double amt = Double.parseDouble(scanner.nextLine());
                        bank.transfer(account, toId, amt);
                    } catch (AccountNotFoundException |
                             InsufficientFundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.println("--- Transaction History ---");
                    account.getTransactions()
                            .forEach(System.out::println);
                }
                case 6 -> { loggedIn = false;
                    System.out.println("Logged out."); }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}