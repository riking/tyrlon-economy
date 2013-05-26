package me.riking.tyrlon;

import java.util.List;
import java.util.Map;

import me.riking.tyrlon.datamodel.PlayerAccount;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class TyrlonEconomy implements Economy {
    private Tyrlon plugin;

    public TyrlonEconomy(Tyrlon plugin) {
        this.plugin = plugin;
    }

    @Override
    public double getBalance(String player) {
        PlayerAccount account = plugin.getAccountStorage().playerAccounts.get(player);
        if (account == null) {
            return 0;
        }
        return account.getBalance();
    }

    @Override
    public double getBalance(String player, String world) {
        // TODO per-world support
        return getBalance(player);
    }

    @Override
    public EconomyResponse depositPlayer(String player, double amount) {
        PlayerAccount account = plugin.getAccountStorage().playerAccounts.get(player);
        if (account == null) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "No such account");
        }
        account.deposit(amount);
        return new EconomyResponse(amount, account.getBalance(), ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(String player, String world, double amount) {
        // TODO per-world support
        return depositPlayer(player, amount);
    }

    @Override
    public boolean has(String player, double amount) {
        PlayerAccount account = plugin.getAccountStorage().playerAccounts.get(player);
        if (account == null) {
            return false;
        }
        return account.has(amount);
    }

    @Override
    public boolean has(String player, String world, double amount) {
        // TODO per-world support
        return has(player, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String player, double amount) {
        PlayerAccount account = plugin.getAccountStorage().playerAccounts.get(player);
        if (account == null) {
            return new EconomyResponse(0, 0, ResponseType.FAILURE, "No such account");
        }
        account.withdraw(amount);
        return new EconomyResponse(amount, account.getBalance(), ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(String player, String world, double amount) {
        // TODO per-world support
        return withdrawPlayer(player, amount);
    }

    @Override
    public boolean createPlayerAccount(String player) {
        Map<String, PlayerAccount> playerAccounts = plugin.getAccountStorage().playerAccounts;
        if (playerAccounts.containsKey(player)) {
            return false;
        }
        playerAccounts.put(player, new PlayerAccount(player));
        return true;
    }

    @Override
    public boolean createPlayerAccount(String player, String world) {
        // TODO per-world support
        return createPlayerAccount(player);
    }

    @Override
    public boolean hasAccount(String player) {
        Map<String, PlayerAccount> playerAccounts = plugin.getAccountStorage().playerAccounts;
        return playerAccounts.containsKey(player);
    }

    @Override
    public boolean hasAccount(String player, String world) {
        // TODO per-world support
        return hasAccount(player, world);
    }

    /*
     * After noticing that bankHas is supposed to return an EconomyResponse
     * instead of a boolean like the player has(), I refuse to implement bank
     * support.
     */
    public EconomyResponse bankBalance(String bank) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Refuse to implement");
    }
    public EconomyResponse bankDeposit(String bank, double amount) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Refuse to implement");
    }
    public EconomyResponse bankHas(String bank, double amount) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Refuse to implement");
    }
    public EconomyResponse bankWithdraw(String bank, double amount) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Refuse to implement");
    }
    public EconomyResponse createBank(String bank, String player) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Refuse to implement");
    }
    public EconomyResponse deleteBank(String bank) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Refuse to implement");
    }
    public EconomyResponse isBankMember(String bank, String player) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Refuse to implement");
    }
    public EconomyResponse isBankOwner(String bank, String player) {
        return new EconomyResponse(0, 0, ResponseType.NOT_IMPLEMENTED, "Refuse to implement");
    }
    public List<String> getBanks() {
        return null;
    }
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public String currencyNamePlural() {
        return plugin.currencyNamePlural;
    }

    @Override
    public String currencyNameSingular() {
        return plugin.currencyName;
    }

    @Override
    public String format(double amount) {
        return String.format("%.2f", amount);
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }


    @Override
    public String getName() {
        return "Tyrlon";
    }

    @Override
    public boolean isEnabled() {
        return plugin.isEnabled();
    }

}
