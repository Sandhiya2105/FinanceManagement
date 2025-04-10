package Test;

import dao.FinanceRepositoryImpl;
import entity.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();

    @Test
    public void testUserCreatedSuccessfully() {
        FinanceRepositoryImpl repo = new FinanceRepositoryImpl();
        String randomUsername = "testuser" + System.currentTimeMillis();
        User user = new User(0, randomUsername, "junitpass", randomUsername + "@example.com");
        assertTrue(repo.createUser(user));
    }
}