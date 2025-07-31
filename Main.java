import java.util.Scanner;

class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of customers: ");
        int numCustomers = scanner.nextInt();
        int[] accountNumbers = new int[numCustomers];
        String[] customerNames = new String[numCustomers];
        double[] balanceAmounts = new double[numCustomers];

        // Minimum balance amount
        final double MIN_BALANCE = 500.0;

        // Input customer details
        for (int i = 0; i < numCustomers; i++) {
            System.out.println("Enter details for customer " + (i + 1) + ":");
            System.out.print("Account Number: ");
            accountNumbers[i] = scanner.nextInt();

            scanner.nextLine(); // Consume newline
            System.out.print("Customer Name: ");
            customerNames[i] = scanner.nextLine();

            System.out.print("Balance Amount: ");
            balanceAmounts[i] = scanner.nextDouble();
            System.out.println();
        }

        // Display customer details and check balances
        System.out.println("Customer Details:");
        for (int i = 0; i < numCustomers; i++) {
            try {
                System.out.println("Account Number: " + accountNumbers[i]);
                System.out.println("Customer Name: " + customerNames[i]);
                System.out.println("Balance Amount: " + balanceAmounts[i]);

                // Check for balance less than the minimum amount
                if (balanceAmounts[i] < MIN_BALANCE) {
                    throw new MyException("Balance amount is less for account number: " + accountNumbers[i]);
                }
                System.out.println("Status: Balance is sufficient.\n");
            } catch (MyException e) {
                System.out.println("Exception: " + e.getMessage() + "\n");
            }
        }

        scanner.close();
        System.out.println("Processing complete.");
    }
}