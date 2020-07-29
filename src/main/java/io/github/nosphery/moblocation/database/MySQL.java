package io.github.nosphery.moblocation.database;

import io.github.nosphery.moblocation.MobLocationPlugin;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.sql.*;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
public class MySQL {

    private Connection connection;

    /**
     * @throws SQLException
     */
    public void start() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        File data = new File(MobLocationPlugin.getInstance().getDataFolder(), "factions.db");
        this.connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", data));
    }

    /**
     * @throws SQLException
     */
    public void refresh() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) this.start();
    }

    /**
     * @param query
     * @return PreparedStatement
     * @throws SQLException
     */
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return this.connection.prepareStatement(query);
    }

    /**
     * @param query
     * @return Boolean
     * @throws SQLException
     */
    public Boolean execute(String query) throws SQLException {
        return this.connection.createStatement().execute(query);
    }

    /**
     * @return Connection
     */
    public Connection getConnection() { return this.connection; }
}
