package me.riking.tyrlon.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import me.riking.tyrlon.Database;

public abstract class SqlDatabase implements Database {
    Connection connection;
    String database;

    PreparedStatement newPlayer;
    PreparedStatement savePlayer;
    PreparedStatement deletePlayer;
    PreparedStatement newBank;
    PreparedStatement saveBankMoney;
    PreparedStatement saveBankFull;
    PreparedStatement deleteBank;
    PreparedStatement getPlayers;
    PreparedStatement getBanks;

    public SqlDatabase(Connection connection, String database) {
        this.connection = connection;
        this.database = database;
    }

    public void setupTables() throws SQLException {
        // Let's actually use the database they're asking us to use, mmkay?
        PreparedStatement createDb = connection.prepareStatement("CREATE DATABASE ? IF NOT EXISTS");
        PreparedStatement useDb = connection.prepareStatement("USE ?");
        createDb.setString(0, database);
        useDb.setString(0, database);
        createDb.execute();
        useDb.execute();
        createDb.close();
        useDb.close();
        Statement s1 = connection.createStatement();
        s1.execute("CREATE TABLE players IF NOT EXISTS ( name varchar(200) NOT NULL PRIMARY KEY, money double precision NOT NULL )");
        s1.execute("CREATE TABLE banks IF NOT EXISTS ( bankname varchar(200) NOT NULL PRIMARY KEY, owner varchar(200), money double precision NOT NULL, FOREIGN KEY (owner) REFERENCES players(name) )");
        s1.close();
    }

    public void prepareStatements() throws SQLException {
        newPlayer = connection.prepareStatement("INSERT INTO players(name, money) VALUES (?, ?)");
        newBank = connection.prepareStatement("INSERT INTO BANKS(bankname, owner, money) VALUES (?, ?, ?)");
        savePlayer = connection.prepareStatement("UPDATE players SET money=? WHERE name=?");
        saveBankMoney = connection.prepareStatement("UPDATE banks SET money=? WHERE bankname=?");
        saveBankFull = connection.prepareStatement("UPDATE banks SET money=?,owner=? WHERE bankname=?");
        deletePlayer = connection.prepareStatement("DELETE FROM players WHERE name=?");
        deletePlayer = connection.prepareStatement("DELETE FROM banks WHERE name=?");
        getPlayers = connection.prepareStatement("SELECT name, money FROM players");
        getBanks = connection.prepareStatement("SELECT bankname, owner, money FROM banks");
    }
}
