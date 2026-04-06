import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountId;
    private String holderName;
    private String password;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountId, String holderName,
                   String password, double initialDeposit) {
        this.accountId = accountId;
        this.holderName = holderName;
        this.password = password;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        transactions.add(new Transaction("DEPOSIT",
                initialDeposit, "Initial deposit"));
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("DEPOSIT",
                amount, "Deposit"));
    }

    public void withdraw(double amount)
            throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Insufficient funds. Current balance: " + balance);
        }
        balance -= amount;
        transactions.add(new Transaction("WITHDRAW",
                amount, "Withdrawal"));
    }

    public String getAccountId() { return accountId; }
    public String getHolderName() { return holderName; }
    public String getPassword()   { return password; }
    public double getBalance()    { return balance; }
    public List<Transaction> getTransactions() { return transactions; }
}