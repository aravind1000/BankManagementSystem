import java.util.*;

class Account {
    String name, phone;

    Account(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

class Activity {
    String status;
    int amount, balance;

    Activity(String status, int amount, int balance) {
        this.status = status;
        this.amount = amount;
        this.balance = balance;
    }
}

class BankOperations {
    private final Map<Integer, Account> accounts = new HashMap<>();
    private final Map<Integer, List<Activity>> transactions = new HashMap<>();
    private final Map<Integer, Integer> balances = new HashMap<>();
    private int accountIndex = 0;

    public int createAccount(String name, String phone) {
        Account account = new Account(name, phone);
        accounts.put(accountIndex, account);
        transactions.put(accountIndex, new ArrayList<>());
        balances.put(accountIndex, 0);
        return accountIndex++;
    }

    public boolean closeAccount(String name) {
        for (int index : accounts.keySet()) {
            if (accounts.get(index).name.equals(name)) {
                accounts.remove(index);
                transactions.remove(index);
                balances.remove(index);
                return true;
            }
        }
        return false;
    }

    public Account getAccount(int index) {
        return accounts.get(index);
    }

    public void deposit(int index, int amount) {
        balances.put(index, balances.get(index) + amount);
        addTransaction(index, "Credited", amount, balances.get(index));
    }

    public boolean withdraw(int index, int amount) {
        int currentBalance = balances.get(index);
        if (amount <= currentBalance) {
            balances.put(index, currentBalance - amount);
            addTransaction(index, "Debited", amount, balances.get(index));
            return true;
        }
        return false;
    }

    public int getBalance(int index) {
        return balances.get(index);
    }

    public List<Activity> getTransactionHistory(int index) {
        return transactions.get(index);
    }

    private void addTransaction(int index, String status, int amount, int balance) {
        transactions.get(index).add(new Activity(status, amount, balance));
    }
}

public class RealBank {
    private static final Scanner sc = new Scanner(System.in);
    private static final BankOperations bankOperations = new BankOperations();

    public static void main(String[] args) {
        System.out.println("\t\t\t---------- Welcome to Programmer's Bank ----------");

        while (true) {
            displayMainMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1 -> handleAccountCreation();
                case 2 -> handleSavingsAccount();
                case 3 -> handleAccountClosure();
                case 4 -> {
                    System.out.println("---------- Thank You for Visiting! Visit Again! ----------");
                    return;
                }
                default -> System.out.println("Invalid Option. Please Try Again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("""
                \n1. Create Account
                2. Savings Account
                3. Close Account
                4. Exit\n
                """);
    }

    private static void handleAccountCreation() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = sc.nextLine();
        int accountNumber = bankOperations.createAccount(name, phone);
        System.out.println("Account Created Successfully! Your Unique Account Number is: " + accountNumber);
    }

    private static void handleSavingsAccount() {
        System.out.print("Enter your account number: ");
        int accountIndex = sc.nextInt();
        sc.nextLine();

        Account account = bankOperations.getAccount(accountIndex);
        if (account != null) {
            while (true) {
                displaySavingsMenu();
                int choice = getUserChoice();
                switch (choice) {
                    case 1 -> handleDeposit(accountIndex);
                    case 2 -> handleWithdrawal(accountIndex);
                    case 3 -> displayTransactionHistory(accountIndex);
                    case 4 -> displayBalance(accountIndex);
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Invalid Option. Please Try Again.");
                }
            }
        } else {
            System.out.println("No account found with the provided number.");
        }
    }

    private static void displaySavingsMenu() {
        System.out.println("""
                \n1. Deposit
                2. Withdraw
                3. Transaction History
                4. Balance Inquiry
                5. Exit\n
                """);
    }

    private static void handleDeposit(int accountIndex) {
        System.out.print("Enter the amount to deposit: ");
        int amount = sc.nextInt();
        bankOperations.deposit(accountIndex, amount);
        System.out.println("Amount Deposited Successfully! Current Balance: " + bankOperations.getBalance(accountIndex));
    }

    private static void handleWithdrawal(int accountIndex) {
        System.out.print("Enter the amount to withdraw: ");
        int amount = sc.nextInt();
        if (bankOperations.withdraw(accountIndex, amount)) {
            System.out.println("Amount Withdrawn Successfully! Current Balance: " + bankOperations.getBalance(accountIndex));
        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    private static void displayTransactionHistory(int accountIndex) {
        List<Activity> history = bankOperations.getTransactionHistory(accountIndex);
        if (history.isEmpty()) {
            System.out.println("No Transactions Found.");
        } else {
            System.out.println("Transaction History:");
            for (Activity activity : history) {
                System.out.printf("Status: %s, Amount: %d, Balance: %d%n", activity.status, activity.amount, activity.balance);
            }
        }
    }

    private static void displayBalance(int accountIndex) {
        System.out.println("Your Current Balance: " + bankOperations.getBalance(accountIndex));
    }

    private static void handleAccountClosure() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        if (bankOperations.closeAccount(name)) {
            System.out.println("Account Closed Successfully.");
        } else {
            System.out.println("Account Not Found. Please Check the Name.");
        }
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return sc.nextInt();
    }
}
