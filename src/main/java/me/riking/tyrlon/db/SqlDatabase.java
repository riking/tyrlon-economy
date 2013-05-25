package me.riking.tyrlon.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import me.riking.tyrlon.Database;

public abstract class SqlDatabase implements Database {
    Connection connection;
    PreparedStatement savePlayer;
    String database;

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
        connection.createStatement().execute("CREATE TABLE players IF NOT EXISTS (" +
        		"name varchar(200) NOT NULL," +
        		"money double precision NOT NULL" +
        		")");
        connection.createStatement().execute("CREATE TABLE banks IF NOT EXISTS (" +
        		"bankname varchar(200) NOT NULL," +
        		"owner varchar(200)," +
        		"FOREIGN KEY (owner) REFERENCES players(name)" +
        		")");
    }
}
