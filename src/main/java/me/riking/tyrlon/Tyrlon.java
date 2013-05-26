package me.riking.tyrlon;

import java.io.IOException;

import me.riking.tyrlon.db.sql.MysqlDatabase;
import me.riking.tyrlon.db.yaml.YamlDatabase;
import me.riking.tyrlon.pruning.PrunePlayersTask;

import net.milkbowl.vault.economy.Economy;
import org.apache.commons.lang.Validate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class Tyrlon extends JavaPlugin {
    private AccountStorage accounts;
    private static Tyrlon instance;

    public static Tyrlon getInstance() {
        return instance;
    }

    public AccountStorage getAccountStorage() {
        return accounts;
    }

    private Database dbase;

    public boolean flushEachTransaction;
    public int pruneBatchSize;
    public long pruneMaxAge;
    public long prunePeriod;
    public String currencyName;
    public String currencyNamePlural;

    public void onLoad() {
        instance = this;
        dbase = null;
        getServer().getServicesManager().register(Economy.class, new TyrlonEconomy(this), this, ServicePriority.Normal);
    }

    @Override
    public void onEnable() {
        instance = this;
        accounts = new AccountStorage(null);
        loadConfig();
        try {
            loadDatabaseConfig();
        } catch (Exception e) {
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        dbase.loadBlocking(accounts);

        getServer().getScheduler().runTaskTimerAsynchronously(this, new PrunePlayersTask(this), 50, prunePeriod);
    }

    @Override
    public void saveConfig() {
        super.saveDefaultConfig();
        dbase.saveBlocking(accounts);
    }

    @Override
    public void onDisable() {
        saveConfig();
        try {
            dbase.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        dbase = null;
        instance = null;
    }

    private void loadConfig() {
        ConfigurationSection config = getConfig();
        pruneMaxAge = config.getLong("Prune.MaxAgeMillis", 262800000); // milliseconds in a month
        pruneBatchSize = config.getInt("Prune.MaxBatchSize", 400); // players to check each tick
        prunePeriod = config.getLong("Prune.PeriodTicks", 360000); // milliseconds in a month
        flushEachTransaction = config.getBoolean("FlushEachChange", false);
        currencyName = config.getString("CurrencyName", "Dollar");
        currencyNamePlural = config.getString("CurrencyNamePlural", "Dollars");
        if (!config.contains("database")) {
            config.createSection("database");
            config.createSection("database.yaml");
            config.createSection("database.mysql");
        }
    }

    private void loadDatabaseConfig() throws RuntimeException {
        ConfigurationSection config = getConfig();
        if (dbase != null) {
            getLogger().info("Using database implementation from other plugin (" + dbase.getClass() + ")");
            config.set("DatabaseType", "external");
        } else {
            String type = config.getString("DatabaseType", "yaml");
            ConfigurationSection section = config.getConfigurationSection("database."+type);
            if (type.equalsIgnoreCase("yaml")) {
                if (section == null) {
                    section = config.createSection("database.yaml");
                }
                dbase = new YamlDatabase(this, section);
            } else if (type.equalsIgnoreCase("mysql")) {
                if (section == null) {
                    section = config.createSection("database.mysql");
                }
                dbase = new MysqlDatabase(this, section);
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

    public Database getCurrentDatabase() {
        return dbase;
    }
}
