import java.util.Scanner;

class BankingApp {
    private final String accountHolderName;
    private double balance;

    public BankingApp(String name, double initialBalance) {
        this.accountHolderName = name;
        this.balance = initialBalance;
        showMenu();
    }

    // Method to display menu options
    private void showMenu() {
        char option;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nWelcome, " + accountHolderName + "!");
        System.out.println("Your current balance is: " + balance);

        do {
            System.out.println("\n=== Main Menu ===");
            System.out.println("A. Check Balance");
            System.out.println("B. Deposit");
            System.out.println("C. Withdraw");
            System.out.println("D. Exit");
            System.out.print("Enter an option: ");
            option = sc.next().charAt(0);

            switch (option) {
                case 'A':
                case 'a':
                    checkBalance();
                    break;
                case 'B':
                case 'b':
                    deposit();
                    break;
                case 'C':
                case 'c':
                    withdraw();
                    break;
                case 'D':
                case 'd':
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option. Please enter A, B, C, or D.");
                    break;
            }
        } while (option != 'D' && option != 'd');

        sc.close();
    }

    // Method to check balance
    private void checkBalance() {
        System.out.println("Your balance is: " + balance);
    }

    // Method to deposit money
    private void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
            checkBalance();
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money
    private void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
            checkBalance();
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
}

public class Task2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Collect user information
        System.out.print("Enter account holder's name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial balance: ");
        double initialBalance = sc.nextDouble();

        // Create a new BankAccount object, which shows the showMenu() method
        new BankingApp(name, initialBalance);
    }
}