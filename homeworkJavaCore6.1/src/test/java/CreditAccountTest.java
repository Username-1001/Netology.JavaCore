import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditAccountTest {
    CreditAccount account;

    @BeforeEach
    void setUp() {
        account = new CreditAccount();
    }

    @Test
    void pay() {
        int amount = 10, amountNegative = -10, expected = -10;

        assertTrue(account.pay(amount));
        assertFalse(account.pay(amountNegative));
        assertEquals(account.getMoney(), expected);
    }

    @Test
    void transfer() {
        int amountNegative = -10, amount = 10, expected = -10;
        Account accountTransferTo = new CheckingAccount();

        assertFalse(account.transfer(accountTransferTo, amountNegative));
        assertTrue(account.transfer(accountTransferTo, amount));
        assertEquals(account.getMoney(), expected);
    }

    @Test
    void addMoney() {
        int amountNegative = -10, amount = 10, money = -20, expected = -10;

        assertFalse(account.addMoney(amountNegative));
        assertFalse(account.addMoney(amount));

        account.setMoney(money);
        assertTrue(account.addMoney(amount));
        assertEquals(account.getMoney(), expected);
    }
}