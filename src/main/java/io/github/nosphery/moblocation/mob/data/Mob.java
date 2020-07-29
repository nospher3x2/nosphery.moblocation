package io.github.nosphery.moblocation.mob.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

/**
 * @author oNospher
 **/
@AllArgsConstructor
@Getter
public class Mob {

    private String tag;
    private EntityType entityType;
    private Location location;

}
