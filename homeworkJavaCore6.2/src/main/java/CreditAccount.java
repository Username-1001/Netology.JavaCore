public class CreditAccount extends Account{
    @Override
    protected boolean pay(int amount) {
        if (amount > 0) {
            System.out.println("Выполнена оплата со счета на сумму: " + amount);
            System.out.println();
            money -= amount;
            return true;
        }
        return  false;
    }

    @Override
    protected boolean transfer(Account account, int amount) {
        if (amount < 0) {
            return false;
        }

        if (account.addMoney(amount)) {
            System.out.println("Выполнен перевод на сумму " + amount + " руб.");
            System.out.println();
            money -= amount;
            return true;
        }
        System.out.println("Перевод не выполнен");
        System.out.println();
        return  false;
    }

    @Override
    protected boolean addMoney(int amount) {
        if (amount < 0){
            return false;
        }
        if (amount > Math.abs(money)) {
            System.out.println("Невозможно пополнить на данную сумму, задолженность составляет: " + Math.abs(money));
            System.out.println();
            return false;
        }

        System.out.println("Кредитный счет пополнен на сумму: " + amount);
        System.out.println();
        money += amount;
        return  true;
    }
}
