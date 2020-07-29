package io.github.nosphery.moblocation;

import io.github.nosphery.moblocation.manager.StartManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author oNospher
 **/
public class MobLocationPlugin extends JavaPlugin {

    private static MobLocationPlugin instance;
    @Getter
    private Boolean freeze;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        new StartManager();
        this.freeze = this.getConfig().getBoolean(
                "settings.general.freeze_mobs"
        );

    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
    }

    public static MobLocationPlugin getInstance() {
        return instance;
    }
}
