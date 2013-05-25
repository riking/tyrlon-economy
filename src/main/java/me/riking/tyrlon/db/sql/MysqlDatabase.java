package me.riking.tyrlon.db.sql;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

import me.riking.tyrlon.AccountStorage;
import me.riking.tyrlon.Tyrlon;
import me.riking.tyrlon.datamodel.BankAccount;
import me.riking.tyrlon.datamodel.PlayerAccount;

public class MysqlDatabase extends SqlDatabase {
    Tyrlon plugin;

    public MysqlDatabase(Tyrlon plugin, ConfigurationSection config) {
        super(makeConnection(config), config.getString("database"));
        this.plugin = plugin;
    }

    private static Connection makeConnection(ConfigurationSection config) {
        // TODO
        return null;
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
    public void pruneTheseOldAccounts(Set<String> players) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveBlocking(AccountStorage accounts) {
        // TODO Auto-generated method stub

    }

    @Override
    public void close() throws IOException {
        super.close();
        // TODO
    }
}
