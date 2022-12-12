import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class CreditAccountTest {
    CreditAccount account;

    @BeforeEach
    void setUp() {
        account = new CreditAccount();
    }

    @Test
    public void testCheckingAccountInheritance() {
        assertThat(account.getClass(), typeCompatibleWith(Account.class));
    }

    @Test
    void pay() {
        int amount = 10, amountNegative = -10, expected = -10;

        assertThat(account.pay(amount), is(true));
        assertThat(account.pay(amountNegative), is(false));
        assertThat(account.getMoney(), equalTo(expected));
    }

    @Test
    void transfer() {
        int amountNegative = -10, amount = 10, expected = -10;
        Account accountTransferTo = new CheckingAccount();

        assertThat(account.transfer(accountTransferTo, amountNegative), is(false));
        assertThat(account.transfer(accountTransferTo, amount), is(true));
        assertThat(account.getMoney(), equalTo(expected));
    }

    @Test
    void addMoney() {
        int amountNegative = -10, amount = 10, money = -20, expected = -10;

        assertThat(account.addMoney(amountNegative), is(false));
        assertThat(account.addMoney(amount), is(false));

        account.setMoney(money);

        assertThat(account.addMoney(amount), is(true));
        assertThat(account.getMoney(), equalTo(expected));
    }
}