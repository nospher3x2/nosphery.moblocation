package io.github.nosphery.moblocation.manager;

import io.github.nosphery.moblocation.RepositoryProvider;
import io.github.nosphery.moblocation.MobLocationPlugin;
import io.github.nosphery.moblocation.database.manager.MySQLManager;
import io.github.nosphery.moblocation.database.runnable.MySQLRefreshRunnable;
import io.github.nosphery.moblocation.util.ClassGetter;
import io.github.nosphery.moblocation.util.inventory.InventoryBuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * @author oNospher
 **/
public class StartManager {

    public StartManager() {
        new ListenerManager();
        new MySQLManager();
        new RepositoryProvider();
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(MobLocationPlugin.getInstance(), new MySQLRefreshRunnable(), 0L, 20L * 60);
    }

}

class ListenerManager {
    /**
     * Registering all listeners on call constructor of this class
     */
    ListenerManager() {
        ClassGetter.getClassesForPackage(MobLocationPlugin.getInstance(), "io.github.nosphery").forEach(clazz -> {
            if (Listener.class.isAssignableFrom(clazz) && !clazz.equals(InventoryBuilder.class)) {
                try {
                    Listener listener = (Listener) clazz.newInstance();
                    Bukkit.getPluginManager().registerEvents(listener, MobLocationPlugin.getInstance());
                } catch (InstantiationException | IllegalAccessException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}


