package io.github.nosphery.moblocation.mob.manager;

import io.github.nosphery.moblocation.database.table.TableRow;
import io.github.nosphery.moblocation.mob.data.Mob;
import io.github.nosphery.moblocation.util.LocationSerialize;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

/**
 * @author oNospher
 **/
public class MobManager {

    public static Mob toMob(TableRow tableRow) {
        Location location = LocationSerialize.toLocation(tableRow.getString("location"));
        EntityType entityType = EntityType.valueOf(tableRow.getString("entity"));
        return new Mob(
                tableRow.getString("faction"),
                entityType,
                location
        );
    }
}
