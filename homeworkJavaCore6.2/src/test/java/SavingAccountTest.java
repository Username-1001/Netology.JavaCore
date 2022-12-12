import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class SavingAccountTest {
    SavingAccount account;

    @BeforeEach
    void setUp() {
        account = new SavingAccount();
    }

    @Test
    public void testCheckingAccountInheritance() {
        assertThat(account.getClass(), typeCompatibleWith(Account.class));
    }

    @Test
    void pay() {
        int amount = 10, expected = 0;

        assertThat(account.pay(amount), is(false));
        assertThat(account.getMoney(), equalTo(expected));
    }

    @Test
    void transfer() {
        int amount = 10, amountNegative = -10, money = 20, expected = 10;
        Account accountTransferTo = new CheckingAccount();

        assertThat(account.transfer(accountTransferTo, amount), is(false));

        account.setMoney(money);

        assertThat(account.transfer(accountTransferTo, amountNegative), is(false));
        assertThat(account.transfer(accountTransferTo, amount), is(true));
        assertThat(account.getMoney(), equalTo(expected));
    }

    @Test
    void addMoney() {
        int amount = 10, amountNegative = -10, expected = 10;

        assertThat(account.addMoney(amount), is(true));
        assertThat(account.addMoney(amountNegative), is(false));
        assertThat(account.getMoney(), equalTo(expected));
    }
}