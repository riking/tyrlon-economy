package me.riking.tyrlon.datamodel;

public class BasicAccount implements Account {
    protected String name;
    protected double balance;
    protected boolean dirty;

    public BasicAccount(String name) {
        this(name, 0);
    }

    public BasicAccount(String name, double initial) {
        this.name = name;
        this.balance = initial;
        setDirty();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double money) {
        balance = money;
        setDirty();
    }

    @Override
    public void deposit(double money) {
        balance += money;
        setDirty();
    }

    @Override
    public void withdraw(double money) {
        balance -= money;
        setDirty();
    }

    @Override
    public boolean has(double money) {
        return balance >= money;
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void setClean() {
        dirty = false;
    }

    public void setDirty() {
        dirty = true;
    }

    public String toString() {
        return getClass().getName() + "(" + getName() + ": " + String.format("%.2f", getBalance() + ")");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = Account.class.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Account))
            return false;
        Account other = (Account) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;
        return true;
    }
}
