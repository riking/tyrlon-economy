package me.riking.tyrlon;

import java.io.Closeable;
import java.util.Collection;
import java.util.Set;

import me.riking.tyrlon.datamodel.BankAccount;
import me.riking.tyrlon.datamodel.PlayerAccount;

/**
 * All the methods in the Database interface may call asynchronous tasks,
 * unless specified otherwise.
 */
public interface Database extends Closeable {
    public void saveSinglePlayer(PlayerAccount account);

    public void saveAllPlayers(Collection<PlayerAccount> accounts);

    public void saveSingleBank(BankAccount account);

    public void saveAllBanks(Collection<BankAccount> banks);

    public void removeBank(BankAccount account);

    public void loadAllPlayers(AccountStorage destination);

    public void loadAllBanks(AccountStorage destination);

    /**
     * This method blocks until {@link #loadAllPlayers(AccountStorage)} and
     * {@link #loadAllBanks(AccountStorage)} both complete, using the given
     * AccountStorage for the parameter.
     *
     * @param destination parameter to pass
     */
    public void loadBlocking(AccountStorage destination);

    /**
     * This method blocks until {@link #saveAllPlayers(Collection)} and
     * {@link #saveAllBanks(Collection)} both complete, using the account
     * collections in the given AccountStorage.
     *
     * @param accounts AccountStorage to use
     */
    public void saveBlocking(AccountStorage accounts);

    /**
     * This method should be <b>called</b> asynchronously and should not be
     * concerned with hanging the main server thread.
     * @param players TODO
     */
    public void pruneTheseOldAccounts(Set<String> players);
}
