import java.util.ArrayList;
import java.util.Scanner;

class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}

class Customer {
    int accountNumber;
    String name;
    double balance;

    public Customer(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }
}

public class Banking {
    static final double MIN_BALANCE = 500.0;
    static ArrayList<Customer> customers = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Mini Banking Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Show Account Details");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Check Balance");
            System.out.println("6. View All Customers");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    showAccountDetails();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    viewAllCustomers();
                    break;
                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

    // Create a new account
    public static void createAccount() {
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        customers.add(new Customer(accNo, name, balance));
        System.out.println("Account created successfully!");
    }

    // Show details for one account
    public static void showAccountDetails() {
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();

        for (Customer c : customers) {
            if (c.accountNumber == accNo) {
                System.out.println("\nAccount Number: " + c.accountNumber);
                System.out.println("Customer Name: " + c.name);
                System.out.println("Balance: " + c.balance);
                return;
            }
        }
        System.out.println("Account not found!");
    }

    // Deposit money
    public static void depositMoney() {
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();

        for (Customer c : customers) {
            if (c.accountNumber == accNo) {
                System.out.print("Enter Amount to Deposit: ");
                double amount = scanner.nextDouble();
                c.balance += amount;
                System.out.println("Deposit successful. New Balance: " + c.balance);
                return;
            }
        }
        System.out.println("Account not found!");
    }

    // Withdraw money
    public static void withdrawMoney() {
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();

        for (Customer c : customers) {
            if (c.accountNumber == accNo) {
                System.out.print("Enter Amount to Withdraw: ");
                double amount = scanner.nextDouble();

                if (c.balance - amount < 0) {
                    System.out.println("Insufficient funds!");
                } else {
                    c.balance -= amount;
                    System.out.println("Withdrawal successful. New Balance: " + c.balance);
                }
                return;
            }
        }
        System.out.println("Account not found!");
    }

    // Check balance for one account
    public static void checkBalance() {
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();

        for (Customer c : customers) {
            if (c.accountNumber == accNo) {
                try {
                    if (c.balance < MIN_BALANCE) {
                        throw new MyException("Low balance: " + c.balance);
                    } else {
                        System.out.println("Current Balance: " + c.balance);
                    }
                } catch (MyException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Account not found!");
    }

    // View all customers
    public static void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found!");
            return;
        }

        System.out.println("\n=== All Customers in Bank ===");
        for (Customer c : customers) {
            System.out.println("Account Number: " + c.accountNumber);
            System.out.println("Customer Name: " + c.name);
            System.out.println("Balance: " + c.balance);
            System.out.println("------------------------");
        }
    }
}
