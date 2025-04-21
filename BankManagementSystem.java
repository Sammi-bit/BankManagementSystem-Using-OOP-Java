import java.util.ArrayList;
import java.util.Scanner;

// Account class: represents a bank account
class Account {
    private int accountNumber;
    private String holderName;
    private double balance;

    public Account(int accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = 0.0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void displayInfo() {
        System.out.println("Account Number: " + accountNumber +
                ", Holder: " + holderName +
                ", Balance: $" + balance);
    }
}

// Bank class: manages multiple accounts
class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();
    private int nextAccountNumber = 1001;

    public Account createAccount(String name) {
        Account acc = new Account(nextAccountNumber++, name);
        accounts.add(acc);
        return acc;
    }

    public Account findAccount(int accNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNumber) {
                return acc;
            }
        }
        return null;
    }

    public void displayAllAccounts() {
        for (Account acc : accounts) {
            acc.displayInfo();
        }
    }
}

// Main class
public class BankManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        int choice;

        do {
            System.out.println("\n=== Bank Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Show All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // buffer clear

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    Account newAcc = bank.createAccount(name);
                    System.out.println("Account created successfully!");
                    newAcc.displayInfo();
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    int depAccNum = scanner.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double depAmount = scanner.nextDouble();
                    Account depAcc = bank.findAccount(depAccNum);
                    if (depAcc != null) {
                        depAcc.deposit(depAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    int withAccNum = scanner.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double withAmount = scanner.nextDouble();
                    Account withAcc = bank.findAccount(withAccNum);
                    if (withAcc != null) {
                        withAcc.withdraw(withAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    int balAccNum = scanner.nextInt();
                    Account balAcc = bank.findAccount(balAccNum);
                    if (balAcc != null) {
                        balAcc.displayInfo();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    bank.displayAllAccounts();
                    break;

                case 6:
                    System.out.println("Exiting. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        scanner.close();
    }
}
