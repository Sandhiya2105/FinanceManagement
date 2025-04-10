package dao;

import entity.Expense;
import entity.User;
import java.util.List;

public interface IFinanceRepository {
    boolean createUser(User user);
    boolean createExpense(Expense expense);
    boolean deleteUser(int userId);
    boolean deleteExpense(int expenseId);
    boolean updateExpense(int userId, Expense expense);
    List<Expense> getAllExpenses(int userId);
}




