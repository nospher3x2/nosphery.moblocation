package io.github.nosphery.moblocation.database.runnable;

import io.github.nosphery.moblocation.database.manager.MySQLManager;

/**
 * @author oNospher
 **/
public class MySQLRefreshRunnable implements Runnable {

    @Override
    public void run() {
        new MySQLManager().refresh();
    }
}
