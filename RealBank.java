import java.util.*;

class Account {
    String name, ph_no;

    Account(String name, String ph_no) {
        this.name = name;
        this.ph_no = ph_no;
    }
}

class SavingsAccount {

    public void deposit(int amount,Map<Integer,Integer> bal,int key) {
    	int amt = bal.get(key);
        amt += amount;
        bal.put(key, amt);
    }

    public void withdraw(int amount,Map<Integer,Integer> bal,int key) {
    	int amt = balance(bal, key);
        if (amount <= amt) {
            amt -= amount;
            bal.put(key, amt);
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    public int balance(Map<Integer,Integer> bal,int key) {
        return bal.get(key);
    }
}

class Activity {
    String status;
    int transaction;
    int remains;

    Activity(String status, int transaction, int remains) {
        this.status = status;
        this.transaction = transaction;
        this.remains = remains;
    }
}

public class RealBank {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Account> accDetails = new HashMap<>();
        Map<Integer,List<Activity>> Trans = new HashMap<>();
        Map<Integer,Integer> bal = new HashMap<>();
        int index = 0; 

        System.out.println("\t\t\t\t\t----------Welcome to Programmer's Bank----------");

        while (true) {
            menu();
            System.out.println("Enter the option to continue....");
            int input = sc.nextInt();
            sc.nextLine();

            if (input == 1) {
                System.out.print("Enter your name: ");
                String name = sc.nextLine();
                System.out.print("Enter your phone number: ");
                String ph_no = sc.nextLine();
                Account acc = new Account(name, ph_no);
                accDetails.put(index, acc);
                Trans.put(index, new ArrayList<>());
                bal.put(index,0);
                System.out.println("Account Created Successfully !! and yours Unique account number is "+index);
                index++;
            } else if (input == 2) {
                System.out.println("Welcome to Savings Account");
                System.out.println("Enter the account index to login: ");
                int accIndex = sc.nextInt();
                sc.nextLine();
                SavingsAccount sacc = new SavingsAccount();
                Account acc = accDetails.get(accIndex);
              
                if (acc != null) {
                    while (true) {
                        menuSaving();
                        int n = sc.nextInt();
                        if (n == 1) {
                            System.out.print("Enter the amount: ");
                            int amount = sc.nextInt();
                            sacc.deposit(amount,bal,accIndex);
                            Activity at = new Activity("Credited", amount, sacc.balance(bal,accIndex));
                            Trans.get(accIndex).add(at);
                            System.out.println("Amount Credited: " + amount + ". Your Current Balance: " + sacc.balance(bal,accIndex));
                        } else if (n == 2) {
                            System.out.print("Enter the amount: ");
                            int amount = sc.nextInt();
                            sacc.withdraw(amount,bal,accIndex);
                            if(amount <= bal.get(accIndex)) {
                            Activity at = new Activity("Debited", amount, sacc.balance(bal,accIndex));
                            Trans.get(accIndex).add(at);
                            System.out.println("Amount Debited: " + amount + ". Your Current Balance: " + sacc.balance(bal,accIndex));
                            }
                        } else if (n == 3) {
                            System.out.println("Transaction History:");
                            for (Activity act : Trans.get(accIndex)) {
                                System.out.println("Status: " + act.status + ", Transaction: " + act.transaction + ", Remaining Balance: " + act.remains);
                            }
                        } else if (n == 4) {
                            System.out.println("Your Balance: " + bal.get(accIndex));
                        } else if (n == 5) {
                            break;
                        }
                    }
                } else {
                    System.out.println("No Account found, Create a new Account");
                }
            } else if (input == 3) {
                System.out.print("Enter your name: ");
                String name = sc.nextLine();
                boolean check = false;
                for (int key : accDetails.keySet()) {
                    if (accDetails.get(key).name.equals(name)) {
                        accDetails.remove(key);
                        Trans.remove(key);
                        bal.remove(key);
                        System.out.println("Account Closed Successfully");
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    System.out.println("Account Not found, Check the name you entered");
                }

            } else if (input == 4) {
                System.out.println("----------Thank You for Visiting, Visit Again !!----------");
                break;
            }
        }
    }

    public static void menuSaving() {
    	System.out.println();
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("3. Transaction History");
        System.out.println("4. Balance Inquiry");
        System.out.println("5. Exit");
        System.out.println();
    }

    public static void menu() {
    	System.out.println();
        System.out.println("1. Account Creation");
        System.out.println("2. Savings Account");
        System.out.println("3. Account closure");
        System.out.println("4. Exit");
        System.out.println();
    }
}
