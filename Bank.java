import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();

    public Account createAccount(String holderName,
                                  String password,
                                  double initialDeposit) {
        String accountId = "ACC" + UUID.randomUUID()
                .toString().substring(0, 6).toUpperCase();
        Account account = new Account(accountId, holderName,
                password, initialDeposit);
        accounts.put(accountId, account);
        System.out.println("Account created! Your Account ID: " + accountId);
        return account;
    }

    public Account login(String accountId, String password)
            throws AccountNotFoundException {
        Account account = accounts.get(accountId);
        if (account == null || !account.getPassword().equals(password)) {
            throw new AccountNotFoundException(
                "Invalid account ID or password.");
        }
        return account;
    }

    public void transfer(Account from, String toAccountId, double amount)
            throws AccountNotFoundException, InsufficientFundsException {
        Account to = accounts.get(toAccountId);
        if (to == null) {
            throw new AccountNotFoundException(
                "Recipient account not found: " + toAccountId);
        }
        from.withdraw(amount);
        to.deposit(amount);
        from.getTransactions().add(new Transaction("TRANSFER OUT",
                amount, "Transferred to " + toAccountId));
        to.getTransactions().add(new Transaction("TRANSFER IN",
                amount, "Received from " + from.getAccountId()));
        System.out.println("Transfer successful!");
    }
}