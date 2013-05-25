package me.riking.tyrlon.db;

import java.util.Collection;

import org.bukkit.configuration.ConfigurationSection;

import me.riking.tyrlon.AccountStorage;
import me.riking.tyrlon.Database;
import me.riking.tyrlon.datamodel.BankAccount;
import me.riking.tyrlon.datamodel.PlayerAccount;

public class YamlDatabase implements Database {
    public YamlDatabase(ConfigurationSection config) {

    }

    @Override
    public void saveSinglePlayer(PlayerAccount account) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveAllPlayers(Collection<PlayerAccount> accounts) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveSingleBank(BankAccount account) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveAllBanks(Collection<BankAccount> banks) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeBank(BankAccount account) {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadAllPlayers(AccountStorage destination) {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadAllBanks(AccountStorage destination) {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadBlocking(AccountStorage destination) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pruneOldAccounts(long maxAgeMillis) {
        // TODO Auto-generated method stub

    }

}
