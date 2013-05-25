package me.riking.tyrlon.db.yaml;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.riking.tyrlon.AccountStorage;
import me.riking.tyrlon.Database;
import me.riking.tyrlon.Tyrlon;
import me.riking.tyrlon.datamodel.BankAccount;
import me.riking.tyrlon.datamodel.PlayerAccount;

public class YamlDatabase implements Database {
    Tyrlon plugin;
    File playerFile;
    File bankFile;
    FileConfiguration players;
    FileConfiguration banks;
    YamlSaveRunnable saver;

    public YamlDatabase(Tyrlon plugin, ConfigurationSection config) {
        this.plugin = plugin;

        config.addDefault("placeholder", "for future expansion");

        playerFile = new File(Tyrlon.getInstance().getDataFolder(), "player_accounts.yml");
        bankFile = new File(Tyrlon.getInstance().getDataFolder(), "bank_accounts.yml");
        try {
            playerFile.createNewFile();
            bankFile.createNewFile(); // if not exists
        } catch (IOException e) {
            e.printStackTrace();
            Tyrlon.getInstance().getLogger().severe("Failed to touch Yaml database files - permission error?");
        }
        players = YamlConfiguration.loadConfiguration(playerFile);
        banks = YamlConfiguration.loadConfiguration(bankFile);
        saver = new YamlSaveRunnable(this);
    }

    @Override
    public void saveSinglePlayer(PlayerAccount account) {
        savePlayer(account);
        saver.scheduleAsync();
    }

    @Override
    public void saveAllPlayers(Collection<PlayerAccount> accounts) {
        for (PlayerAccount ac : accounts) {
            savePlayer(ac);
        }
        saver.scheduleAsync();
    }

    private void savePlayer(PlayerAccount account) {
        if (account.getWorld() == null) {
            players.set(account.getName(), account.getBalance());
        } else {
            ConfigurationSection w = players.getConfigurationSection(account.getWorld());
            if (w == null)
                w = players.createSection(account.getWorld());
            w.set(account.getName(), account.getBalance());
        }
    }

    @Override
    public void saveSingleBank(BankAccount account) {
        saveBank(account);
        saver.scheduleAsync();
    }

    @Override
    public void saveAllBanks(Collection<BankAccount> banks) {
        for (BankAccount ac : banks) {
            saveBank(ac);
        }
        saver.scheduleAsync();
    }

    private void saveBank(BankAccount account) {
        ConfigurationSection bank = banks.getConfigurationSection(account.getName());
        if (bank == null)
            bank = banks.createSection(account.getName());
        bank.set("owner", account.getOwner());
        bank.set("balance", account.getBalance());
    }

    @Override
    public void removeBank(BankAccount account) {
        banks.set(account.getName(), null);
    }

    @Override
    public void loadAllPlayers(AccountStorage destination) {
        for (String name : players.getKeys(false)) {
            destination.playerAccounts.put(name, new PlayerAccount(name, players.getDouble(name, 0)));
        }
    }

    @Override
    public void loadAllBanks(AccountStorage destination) {
        for (String name : banks.getKeys(false)) {
            ConfigurationSection section = banks.getConfigurationSection(name);
            destination.bankAccounts.put(name, new BankAccount(name, section.getString("owner"), section.getDouble("balance", 0)));
        }
    }

    @Override
    public void loadBlocking(AccountStorage destination) {
        loadAllPlayers(destination);
        loadAllBanks(destination);
    }

    @Override
    public void saveBlocking(AccountStorage accounts) {
        saver.run();
    }

    @Override
    public void close() throws IOException {
        saver.run();
    }

    @Override
    public void pruneTheseOldAccounts(Set<String> accounts) {
        for (String acc : accounts) {
            players.set(acc, null);
        }
    }
}
