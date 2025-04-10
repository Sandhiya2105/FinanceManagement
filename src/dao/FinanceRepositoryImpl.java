package dao;

import util.DBConnUtil;
import entity.Expense;
import entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceRepositoryImpl {
    private Connection connection;

    public FinanceRepositoryImpl() {
        connection = DBConnUtil.getConnection();
    }

    // Check if User Exists
    private boolean userExists(int userId) {
        String query = "SELECT 1 FROM Users WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean categoryExists(int categoryId) {
        String query = "SELECT 1 FROM ExpenseCategories WHERE category_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean expenseExists(int expenseId) {
        String query = "SELECT 1 FROM Expenses WHERE expense_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, expenseId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createUser(User user) {
        String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addExpense(Expense expense) {
        if (!userExists(expense.getUserId())) {
            System.out.println("Error: User ID does not exist.");
            return false;
        }
        if (!categoryExists(expense.getCategoryId())) {
            System.out.println("Error: Category ID does not exist.");
            return false;
        }

        String query = "INSERT INTO Expenses (user_id, expense_name, amount, category_id, date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, expense.getUserId());
            pstmt.setString(2, expense.getName());
            pstmt.setDouble(3, expense.getAmount());
            pstmt.setInt(4, expense.getCategoryId());
            pstmt.setDate(5, new java.sql.Date(expense.getDate().getTime()));

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        if (!userExists(userId)) {
            System.out.println("Error: User does not exist.");
            return false;
        }

        String query = "DELETE FROM Users WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteExpense(int expenseId) {
        if (!expenseExists(expenseId)) {
            System.out.println("Error: Expense does not exist.");
            return false;
        }

        String query = "DELETE FROM Expenses WHERE expense_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, expenseId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateExpense(int userId, Expense expense) {
        if (!userExists(userId)) {
            System.out.println("Error: User ID does not exist.");
            return false;
        }
        if (!expenseExists(expense.getId())) {
            System.out.println("Error: Expense ID does not exist.");
            return false;
        }
        if (!categoryExists(expense.getCategoryId())) {
            System.out.println("Error: Category ID does not exist.");
            return false;
        }

        String query = "UPDATE Expenses SET expense_name = ?, amount = ?, category_id = ? WHERE expense_id = ? AND user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, expense.getName());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setInt(3, expense.getCategoryId());
            pstmt.setInt(4, expense.getId());
            pstmt.setInt(5, userId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Expense> getExpensesByUser(int userId) {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM Expenses WHERE user_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Expense expense = new Expense(
                            rs.getInt("expense_id"),
                            rs.getInt("user_id"),
                            rs.getDouble("amount"),
                            rs.getInt("category_id"),
                            rs.getDate("date"),
                            rs.getString("expense_name")
                    );
                    expenses.add(expense);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    // Close connection method
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

