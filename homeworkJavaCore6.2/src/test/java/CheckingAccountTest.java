import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckingAccountTest {
    CheckingAccount account;
    @BeforeEach
    public void setUp() {
        account = new CheckingAccount();
    }

    @Test
    public void testCheckingAccountInheritance() {
        assertThat(account.getClass(), typeCompatibleWith(Account.class));
    }

    @Test
    public void testPayNegativeAmount() {
        int amount = -10;
        assertThat(account.pay(amount), is(false));
    }

    @Test
    public void testPaySuccess() {
        int money = 30, amount = 10, expected = 20;

        account.setMoney(money);
        account.pay(amount);
        assertThat(account.getMoney(), equalTo(expected));
    }

    @Test
    public void testAddMoney() {
        int amount = 10, expected = 10;

        assertThat(account.addMoney(amount), is(true));
        assertThat(account.getMoney(), equalTo(expected));
    }

    @Test
    public void testTransferAmountGreaterThanMoney() {
        int money = 10, amount = 20, expected = 10;

        account.setMoney(money);
        Account accountTransferTo = new CheckingAccount();

        assertThat(account.transfer(accountTransferTo, amount), is(false));
        assertThat(account.getMoney(), equalTo(expected));
    }

    @Test
    public void testTransferSuccess() {
        int money = 20, amount = 10, expected = 10;
        Account accountTransferTo = new CheckingAccount();

        account.setMoney(money);

        assertThat(account.transfer(accountTransferTo, amount), is(true));
        assertThat(account.getMoney(), equalTo(expected));
    }
}
