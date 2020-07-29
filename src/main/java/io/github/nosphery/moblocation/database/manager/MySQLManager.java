package io.github.nosphery.moblocation.database.manager;

import com.google.common.collect.Maps;
import io.github.nosphery.moblocation.database.MySQL;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author oNospher
 **/
public class MySQLManager {
    private static HashMap<String, MySQL> databases = Maps.newHashMap();

    /**
     * Setup all mysql databases on call constructor of this class
     */
    public MySQLManager() {
        MySQLManager.databases.put("general", new MySQL());

        this.start();
    }

    /**
     * @param database
     * @return MySQL
     */
    public static MySQL getMySQL(String database) {
        return MySQLManager.databases.get(database);
    }

    /**
     * Start all mysql databases
     */
    public void start() {
        MySQLManager.databases.forEach((name, mysql) -> {
            try {
                mysql.start();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }

    /**
     * Refresh all mysql databases
     */
    public void refresh() {
        MySQLManager.databases.forEach((name, mysql) -> {
            try {
                mysql.refresh();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }
}
