package Test;

import dao.FinanceRepositoryImpl;
import entity.Expense;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class ExpenseTest {

    FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();

    @Test
    public void testExpenseCreatedSuccessfully() {
        Expense expense = new Expense(0, 1, 1000.0, 1, new Date(), "Food");
        boolean result = financeRepo.addExpense(expense);
        assertTrue(result);
    }
}