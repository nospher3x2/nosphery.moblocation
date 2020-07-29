package io.github.nosphery.moblocation.parent.listeners;

import io.github.nosphery.moblocation.MobLocationPlugin;
import io.github.nosphery.moblocation.parent.data.MobLocation;
import io.github.nosphery.moblocation.parent.manager.MobLocationManager;
import io.github.nosphery.moblocation.mob.data.Mob;
import io.github.nosphery.moblocation.util.FreezeMob;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

import java.util.Arrays;

/**
 * @author oNospher
 **/
public class EntitySpawnTeleportToTrappedLocation implements Listener {

    private final String[] FACTION_ID_BLACKLIST = new String[]{
            Factions.ID_NONE,
            Factions.ID_SAFEZONE,
            Factions.ID_WARZONE
    };

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSpawn(SpawnerSpawnEvent event){
        if (!(event.getEntity() instanceof LivingEntity)) return;
        Entity entity = event.getEntity();
        Location location = entity.getLocation();

        Faction faction = BoardColl.get().getFactionAt(PS.valueOf(location));
        if(Arrays.asList(this.FACTION_ID_BLACKLIST).contains(faction.getId())) return;
        MobLocation mobLocation = MobLocationManager.getMobLocation(faction.getTag());
        if(mobLocation == null) return;

        Mob mob = mobLocation.getMob(entity.getType());

        if (mob != null) {
            Location location1 = mob.getLocation();

            if (location1 != null) {
                if (MobLocationPlugin.getInstance().getFreeze()) {
                    FreezeMob.noAI(entity);
                }

                entity.teleport(location1);
            }
        }
    }
}
