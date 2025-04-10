package main;

import dao.FinanceRepositoryImpl;
import entity.Expense;
import entity.User;
import java.util.*;

public class FinanceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();

        while (true) {
            System.out.println("\nFinance Management System");
            System.out.println("1. Add User");
            System.out.println("2. Add Expense");
            System.out.println("3. Delete User");
            System.out.println("4. Delete Expense");
            System.out.println("5. Update Expense");
            System.out.println("6. View Expenses");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) { // Add User
                System.out.print("Enter Username: ");
                String username = scanner.next();
                System.out.print("Enter Password: ");
                String password = scanner.next();
                System.out.print("Enter Email: ");
                String email = scanner.next();
                User user = new User(0, username, password, email);
                if (financeRepo.createUser(user)) {
                    System.out.println("User created successfully!");
                } else {
                    System.out.println("User creation failed!");
                }
            }
            else if (choice == 2) { // Add Expense
                System.out.print("Enter User ID: ");
                int userId = scanner.nextInt();
                System.out.print("Enter Expense Name: ");
                String name = scanner.next();
                System.out.print("Enter Amount: ");
                double amount = scanner.nextDouble();
                System.out.print("Enter Category ID: ");
                int categoryId = scanner.nextInt();
                Date date = new Date(); // Current date

                Expense expense = new Expense(0, userId, amount, categoryId, date, name);
                if (financeRepo.addExpense(expense)) {
                    System.out.println("Expense added successfully!");
                } else {
                    System.out.println("Expense addition failed!");
                }
            }
            else if (choice == 3) { // Delete User
                System.out.print("Enter User ID to Delete: ");
                int userId = scanner.nextInt();
                if (financeRepo.deleteUser(userId)) {
                    System.out.println("User deleted successfully!");
                } else {
                    System.out.println("User deletion failed!");
                }
            }else if (choice == 4) { // Delete Expense
                System.out.print("Enter Expense ID to Delete: ");
                int expenseId = scanner.nextInt();

                if (financeRepo.deleteExpense(expenseId)) {
                    System.out.println("Expense deleted successfully!");
                } else {
                    System.out.println("Expense deletion failed!");
                }
            }else if (choice == 5) { // Update Expense
                System.out.print("Enter User ID: ");
                int userId = scanner.nextInt();
                System.out.print("Enter Expense ID to Update: ");
                int expenseId = scanner.nextInt();
                System.out.print("Enter New Expense Name: ");
                String name = scanner.next();
                System.out.print("Enter New Amount: ");
                double amount = scanner.nextDouble();
                System.out.print("Enter New Category ID: ");
                int categoryId = scanner.nextInt();

                Expense expense = new Expense(expenseId, userId, amount, categoryId, new Date(), name);

                if (financeRepo.updateExpense(userId, expense)) {
                    System.out.println("Expense updated successfully!");
                } else {
                    System.out.println("Expense update failed!");
                }
            }

            else if (choice == 6) {
                System.out.print("Enter User ID to View Expenses: ");
                int userId = scanner.nextInt();
                List<Expense> expenses = financeRepo.getExpensesByUser(userId);

                if (expenses.isEmpty()) {
                    System.out.println("No expenses found for this user.");
                } else {
                    System.out.println("Expenses:");
                    for (Expense exp : expenses) {
                        System.out.println("ID: " + exp.getId() + ", Name: " + exp.getName() + ", Amount: " + exp.getAmount());
                    }
                }
            }


            else if (choice == 7) { // Exit
                break;
            }
        }
        scanner.close();
    }
}

