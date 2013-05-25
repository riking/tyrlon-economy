package me.riking.tyrlon;

import java.util.List;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class TyrlonEconomy implements Economy {

    @Override
    public EconomyResponse bankBalance(String bank) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String bank, double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse bankHas(String bank, double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String bank, double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse createBank(String bank, String player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean createPlayerAccount(String player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean createPlayerAccount(String player, String world) {
        return createPlayerAccount(player);
    }

    @Override
    public String currencyNamePlural() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String currencyNameSingular() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String bank) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String player, double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String player, String world, double amount) {
        return depositPlayer(player, amount);
    }

    @Override
    public String format(double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int fractionalDigits() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getBalance(String player) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getBalance(String player, String world) {
        return getBalance(player);
    }

    @Override
    public List<String> getBanks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean has(String player, double amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean has(String player, String world, double amount) {
        return has(player, amount);
    }

    @Override
    public boolean hasAccount(String player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasAccount(String player, String world) {
        return hasAccount(player, world);
    }

    @Override
    public boolean hasBankSupport() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public EconomyResponse isBankMember(String bank, String player) {
        return isBankOwner(bank, player);
    }

    @Override
    public EconomyResponse isBankOwner(String bank, String player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public EconomyResponse withdrawPlayer(String player, double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(String player, String world, double amount) {
        return withdrawPlayer(player, amount);
    }

}
