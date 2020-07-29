package io.github.nosphery.moblocation.parent.listeners;

import io.github.nosphery.moblocation.MobLocationPlugin;
import io.github.nosphery.moblocation.parent.data.MobLocation;
import io.github.nosphery.moblocation.RepositoryProvider;
import io.github.nosphery.moblocation.parent.manager.MobLocationManager;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.event.EventFactionsDisband;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author oNospher
 **/
public class FactionDisbandRemoveMobTrappedListener implements Listener {

    @EventHandler
    public void onDeleted(EventFactionsDisband event) {
        Faction faction = event.getFaction();
        String tag = faction.getTag();
        Bukkit.getScheduler().runTask(MobLocationPlugin.getInstance(), ()-> {
            MobLocation mobLocation = MobLocationManager.getMobLocation(tag);
            MobLocationManager.getMobLocations().remove(mobLocation);
            RepositoryProvider.getMobRepository().removeFaction(tag);
        });
    }
}
