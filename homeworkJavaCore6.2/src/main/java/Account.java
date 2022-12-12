public abstract class Account {
    protected int money;

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    protected abstract boolean pay(int amount);

    protected abstract boolean transfer(Account account, int amount);

    protected abstract boolean addMoney(int amount);
}
