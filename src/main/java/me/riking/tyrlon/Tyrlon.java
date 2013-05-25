package me.riking.tyrlon;

import me.riking.tyrlon.db.MysqlDatabase;
import me.riking.tyrlon.db.YamlDatabase;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class Tyrlon extends JavaPlugin {
    private AccountStorage accounts;
    private static Tyrlon instance;

    public static Tyrlon getInstance() {
        return instance;
    }

    public static AccountStorage getAccountStorage() {
        return instance.accounts;
    }

    private Database dbase;

    public long staleAge;
    public boolean flushEachTransaction;

    public void onLoad() {
        instance = this;
        dbase = null;
    }

    @Override
    public void onEnable() {
        instance = this;
        accounts = new AccountStorage(null);
        loadConfig();
        try {
            loadDatabaseConfig();
        } catch (Exception e) {
            getLogger().severe("Failed to load Tyrlon: " + e.getMessage());
        }
        dbase.loadAllBanks(accounts);
        dbase.loadAllPlayers(accounts);
    }

    private void loadConfig() {
        ConfigurationSection config = getConfig();
        staleAge = config.getLong("PlayerDeleteAgeMillis", 262800000); // milliseconds in a month
        flushEachTransaction = config.getBoolean("FlushEachChange", false);
    }

    private void loadDatabaseConfig() throws RuntimeException {
        ConfigurationSection config = getConfig();
        if (dbase != null) {
            getLogger().info("Using database implementation from other plugin (" + dbase.getClass() + ")");
            config.set("DatabaseType", "external");
        } else {
            String type = config.getString("DatabaseType", "yaml");
            if (type.equalsIgnoreCase("yaml")) {
                dbase = new YamlDatabase(config.getConfigurationSection("database.yaml"));
            } else if (type.equalsIgnoreCase("mysql")) {
                dbase = new MysqlDatabase(config.getConfigurationSection("database.mysql"));
            } else {
                // Failure!
                if (type.equalsIgnoreCase("external")) {
                    throw new RuntimeException("External database implementation not found!");
                }
                throw new RuntimeException("Unknown database type: " + type);
            }
        }
    }

    public static void setDatabase(Database newDb) {
        Validate.notNull(newDb, "New database must not be null");
        Tyrlon inst = getInstance();
        Validate.isTrue(inst.dbase == null, "Tyrlon.setDatabase must be called before it is enabled");
        inst.dbase = newDb;
    }
}
