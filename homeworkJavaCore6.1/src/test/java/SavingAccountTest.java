import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {
    SavingAccount account;

    @BeforeEach
    void setUp() {
        account = new SavingAccount();
    }

    @Test
    void pay() {
        int amount = 10, expected = 0;
        assertFalse(account.pay(amount));
        assertEquals(account.getMoney(), expected);
    }

    @Test
    void transfer() {
        int amount = 10, amountNegative = -10, money = 20, expected = 10;
        Account accountTransferTo = new CheckingAccount();

        assertFalse(account.transfer(accountTransferTo, amount));

        account.setMoney(money);
        assertFalse(account.transfer(accountTransferTo, amountNegative));
        assertTrue(account.transfer(accountTransferTo, amount));
        assertEquals(account.getMoney(), expected);
    }

    @Test
    void addMoney() {
        int amount = 10, amountNegative = -10, expected = 10;

        assertTrue(account.addMoney(amount));
        assertFalse(account.addMoney(amountNegative));
        assertEquals(account.getMoney(), expected);
    }
}