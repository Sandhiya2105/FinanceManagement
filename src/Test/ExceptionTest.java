package Test;

import dao.FinanceRepositoryImpl;
import entity.Expense;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ExceptionTest {

    FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();

    @Test
    public void testExceptionForInvalidUserOnExpenseCreation() {
        Expense expense = new Expense(0, -1, 500.0, 1, new Date(), "Invalid User");
        boolean result = financeRepo.addExpense(expense);
        assertFalse(result);
    }

    @Test
    public void testExceptionForInvalidCategory() {
        Expense expense = new Expense(0, 1, 500.0, 999, new Date(), "Invalid Category");
        boolean result = financeRepo.addExpense(expense);
        assertFalse(result);
    }
}


