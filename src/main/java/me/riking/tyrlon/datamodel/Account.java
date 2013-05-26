package me.riking.tyrlon.datamodel;

public interface Account {
    public String getName();

    /**
     * Get the balance of this account.
     *
     * @return current balance
     */
    public double getBalance();

    /**
     * Sets the balance in this account.
     * <p>
     * For tracking purposes, this generates a SET_MONEY report.
     *
     * @param money new balance
     */
    public void setBalance(double money);

    /**
     * Deposit this much money in the account.
     *
     * For tracking purposes, this generates a DEPOSIT report.
     */
    public void deposit(double money);

    /**
     * Withdraw this much money from the account.
     *
     * For tracking purposes, this generates a WITHDRAW report.
     */
    public void withdraw(double money);

    /**
     * Check if this Account has this much money.
     *
     * @return if the account has that much
     */
    public boolean has(double money);

    /**
     * Check if this Account has changed since the last save
     * @return if changes have occured
     */
    public boolean isDirty();

    /**
     * Mark that the Account has been saved
     */
    public void setClean();
}
