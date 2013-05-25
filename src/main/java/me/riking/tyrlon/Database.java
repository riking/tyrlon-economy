package me.riking.tyrlon;

import java.util.Collection;

import me.riking.tyrlon.datamodel.BankAccount;
import me.riking.tyrlon.datamodel.PlayerAccount;

/**
 * All the methods in the Database interface shall call asynchronous tasks,
 * unless specified otherwise.
 */
public interface Database {
    public void saveSinglePlayer(PlayerAccount account);

    public void saveAllPlayers(Collection<PlayerAccount> accounts);

    public void saveSingleBank(BankAccount account);

    public void saveAllBanks(Collection<BankAccount> banks);

    public void removeBank(BankAccount account);

    public void loadAllPlayers(AccountStorage destination);

    public void loadAllBanks(AccountStorage destination);

    /**
     * This method blocks until {@link #loadAllBanks(AccountStorage)} and
     * {@link #loadAllPlayers(AccountStorage)} both complete, using the given
     * AccountStorage for the parameter.
     * 
     * @param destination parameter to pass
     */
    public void loadBlocking(AccountStorage destination);

    public void pruneOldAccounts(long maxAgeMillis);
}
