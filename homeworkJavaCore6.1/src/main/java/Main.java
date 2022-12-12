public class Main {
    public static void main(String[] args) {
        Account accounts[] = {
                new SavingAccount(),
                new CreditAccount(),
                new CheckingAccount(),
        };

        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Работаем со счетом № " + (i + 1) );

            accounts[i].pay(100);

            accounts[i].addMoney(1000);

            accounts[i].pay(500);

            for (int j = 0; j < accounts.length; j++) {
                if (j != i) {
                    accounts[i].transfer(accounts[j], 300);
                }
            }

            accounts[i].addMoney(300);
        }
    }
}
