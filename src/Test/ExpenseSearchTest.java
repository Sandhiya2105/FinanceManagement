package Test;

import dao.FinanceRepositoryImpl;
import entity.Expense;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExpenseSearchTest {

    FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();

    @Test
    public void testSearchExpensesByUser() {
        int userId = 1; // Existing user
        List<Expense> expenses = financeRepo.getExpensesByUser(userId);
        assertNotNull("Expenses list should not be null", expenses);
    }
}
