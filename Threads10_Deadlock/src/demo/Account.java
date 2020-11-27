package demo;

public class Account {
    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account from, Account to, int amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }
}
