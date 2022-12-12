import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckingAccountTest {
    CheckingAccount account;
    @BeforeEach
    public void setUp() {
        account = new CheckingAccount();
    }

    @Test
    public void testPayNegativeAmount() {
        int amount = -10;
        assertFalse(account.pay(amount));
    }

    @Test
    public void testPaySuccess() {
        int money = 30, amount = 10, expected = 20;

        account.setMoney(money);
        account.pay(amount);
        assertEquals(account.getMoney(), expected);
    }

    @Test
    public void testAddMoney() {
        int amount = 10, expected = 10;

        assertTrue(account.addMoney(amount));
        assertEquals(expected, account.getMoney());
    }

    @Test
    public void testTransferAmountGreaterThanMoney() {
        int money = 10, amount = 20, expected = 10;

        account.setMoney(money);
        Account accountTransferTo = new CheckingAccount();

        assertFalse(account.transfer(accountTransferTo, amount));
        assertEquals(account.getMoney(), expected);
    }

    @Test
    public void testTransferSuccess() {
        int money = 20, amount = 10, expected = 10;
        Account accountTransferTo = new CheckingAccount();

        account.setMoney(money);
        assertTrue(account.transfer(accountTransferTo, amount));
        assertEquals(account.getMoney(), expected);
    }
}
