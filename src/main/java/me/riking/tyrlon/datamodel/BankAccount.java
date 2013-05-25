package me.riking.tyrlon.datamodel;

public class BankAccount extends BasicAccount {
    protected String owner;

    public BankAccount(String name, String owner) {
        super(name, 0);
        this.owner = owner;
    }

    public BankAccount(String name, String owner, double initial) {
        super(name, initial);
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String newOwner) {
        owner = newOwner;
    }

    public boolean isOwner(String player) {
        return owner.equals(player);
    }

    public boolean isOwner(PlayerAccount player) {
        return owner.equals(player.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BankAccount other = (BankAccount) obj;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        return true;
    }
}
