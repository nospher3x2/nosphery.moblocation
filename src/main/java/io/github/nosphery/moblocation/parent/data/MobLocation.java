package io.github.nosphery.moblocation.parent.data;


import io.github.nosphery.moblocation.RepositoryProvider;
import io.github.nosphery.moblocation.mob.data.Mob;
import lombok.AllArgsConstructor;
import lombok.Getter;

import org.bukkit.entity.EntityType;

import java.util.List;

/**
 * @author oNospher
 **/
@AllArgsConstructor
@Getter
public class MobLocation {

    private String tag;
    private List<Mob> mobs;

    public Mob getMob(EntityType entityType) {
        return mobs.stream()
                .filter(mob -> mob.getEntityType().equals(entityType))
                .findFirst()
                .orElse(null);
    }

    public boolean hasMob(EntityType entityType) {
        return mobs.stream().anyMatch(mob -> mob.getEntityType().equals(entityType));
    }

    public void addMob(Mob mob) {
        if(!hasMob(mob.getEntityType())) {
            mobs.add(mob);
            RepositoryProvider.getMobRepository().insert(mob);
        }
    }

    public void removeMob(EntityType entityType) {
        Mob mob = this.getMob(entityType);

        mobs.remove(mob);
        RepositoryProvider.getMobRepository().remove(entityType, tag);
    }

}
