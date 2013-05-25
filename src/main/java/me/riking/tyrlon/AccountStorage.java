package me.riking.tyrlon;

import java.util.HashMap;
import java.util.Map;

import me.riking.tyrlon.datamodel.BankAccount;
import me.riking.tyrlon.datamodel.PlayerAccount;

public class AccountStorage {
    /**
     * map of player name -> player account
     */
    public Map<String, PlayerAccount> playerAccounts;
    /**
     * map of bank name -> bank account
     */
    public Map<String, BankAccount> bankAccounts;
    public final String ident;

    public AccountStorage(String name) {
        playerAccounts = new HashMap<String, PlayerAccount>();
        bankAccounts = new HashMap<String, BankAccount>();
        ident = name;
    }
}
