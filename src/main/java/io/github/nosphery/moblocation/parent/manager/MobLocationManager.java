package io.github.nosphery.moblocation.parent.manager;

import com.google.common.collect.Lists;
import io.github.nosphery.moblocation.RepositoryProvider;
import io.github.nosphery.moblocation.mob.data.Mob;
import io.github.nosphery.moblocation.parent.data.MobLocation;

import java.util.List;
import java.util.Objects;

/**
 * @author oNospher
 **/
public class MobLocationManager {

    private static List<MobLocation> mobLocations = Lists.newArrayList();

    public static List<MobLocation> getMobLocations() {
        return mobLocations;
    }

    public static MobLocation getMobLocation(String tag) {
        return mobLocations.stream()
                .filter(Objects::nonNull)
                .filter(mobLocation -> mobLocation.getTag().equalsIgnoreCase(tag))
                .findFirst()
                .orElse(MobLocationManager.find(tag));
    }

    private static MobLocation find(String value) {
        List<Mob> mobs = RepositoryProvider.getMobRepository().find("faction", value);

        if (mobs == null || mobs.isEmpty()) {
            MobLocation mobLocation = new MobLocation(String.valueOf(value), Lists.newArrayList());
            MobLocationManager.getMobLocations().add(mobLocation);
            return mobLocation;
        }

        MobLocation mobLocation = new MobLocation(String.valueOf(value), mobs);
        MobLocationManager.getMobLocations().add(mobLocation);
        return mobLocation;
    }


}
