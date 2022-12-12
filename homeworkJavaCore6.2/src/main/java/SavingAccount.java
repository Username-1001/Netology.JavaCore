public class SavingAccount extends Account{
    @Override
    protected boolean pay(int amount) {
        System.out.println("Невозможно оплатить с данного типа счета");
        System.out.println();
        return false;
    }

    @Override
    protected boolean transfer(Account account, int amount) {
        if (amount < 0){
            return false;
        }

        if (amount > money) {
            System.out.println("Недостаточно средств для выполнения операции");
            System.out.println();
            return false;
        } else if (account.addMoney(amount)) {
            money -= amount;
            System.out.println("Выполнен перевод на сумму " + amount + " руб.");
            System.out.println();
            return true;
        }
        System.out.println("Перевод не выполнен");
        System.out.println();
        return false;
    }

    @Override
    protected boolean addMoney(int amount) {
        if (amount > 0) {
            money += amount;
            System.out.println("Сберегательный счет пополнен на сумму: " + amount);
            System.out.println();
            return true;
        }
        return false;
    }
}
