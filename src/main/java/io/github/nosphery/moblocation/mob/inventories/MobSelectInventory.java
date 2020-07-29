package io.github.nosphery.moblocation.mob.inventories;

import com.google.common.collect.Lists;
import io.github.nosphery.moblocation.MobLocationPlugin;
import io.github.nosphery.moblocation.parent.data.MobLocation;
import io.github.nosphery.moblocation.parent.manager.MobLocationManager;
import io.github.nosphery.moblocation.mob.data.Mob;
import io.github.nosphery.moblocation.util.inventory.InventoryBuilder;
import io.github.nosphery.moblocation.util.inventory.item.ItemBuilder;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * @author oNospher
 **/
public class MobSelectInventory {

    static Rel[] rels = new Rel[]{
            Rel.OFFICER,
            Rel.LEADER
    };

    public static InventoryBuilder getInventory(MPlayer mPlayer) {
        InventoryBuilder inventory = new InventoryBuilder(6, mPlayer.getFactionTag() + " - Selecione o mob");
        Player player = mPlayer.getPlayer();
        Chunk chunk = player.getLocation().getChunk();
        MobLocation mobLocation = MobLocationManager.getMobLocation(mPlayer.getFactionTag());

        MobSelectInventory.getSpawners(chunk).forEach(entityType -> {
            ItemBuilder mobItem = new ItemBuilder(Material.SKULL_ITEM).durability(3).owner(head(entityType)).name((mobLocation.hasMob(entityType) ? "§a" : "§c") + translateMob(entityType.getName())).hideAttributes()
                    .lore((mobLocation.hasMob(entityType) ? "§7Clique para remover a localização atual do(a) " + translateMob(entityType.getName()) : "§7Clique para definir a localização atual do(a) " + translateMob(entityType.getName())))
                    .setConsumer(event -> {
                        if (!Arrays.asList(rels).contains(mPlayer.getRole())) return;
                        Mob mob = new Mob(mPlayer.getFactionTag(), entityType, player.getLocation());
                        if (mobLocation.hasMob(entityType)) {
                            mobLocation.removeMob(entityType);
                            player.sendMessage("§eAgora essa entidade irá nascer em localizações aleatórias.");
                        } else {
                            mobLocation.addMob(mob);
                            player.sendMessage("§eAgora essa entidade irá nascer na sua localização anterior.");
                        }
                        player.closeInventory();
                    });
            inventory.addItem(mobItem);
        });

        inventory.setDesign("XXXXXXXXX", "XOOOOOOOX", "XOOOOOOOX", "XOOOOOOOX", "XOOOOOOOX", "XXXXXXXXX");
        inventory.setCancel(true);
        return inventory;
    }

    private static List<EntityType> getSpawners(Chunk chunk) {
        List<EntityType> spawners = Lists.newArrayList();
        for (BlockState state : chunk.getTileEntities()) {
            if (state instanceof CreatureSpawner) {
                CreatureSpawner spawner = (CreatureSpawner) state;
                if (!spawners.contains(spawner.getSpawnedType()))
                    spawners.add(spawner.getSpawnedType());
            }
        }

        return spawners;
    }


    public static String translateMob(String nome) {
        String ret = "";
        if (nome.equalsIgnoreCase("Spider")) {
            ret = "Aranha";
        }
        if (nome.equalsIgnoreCase("Blaze")) {
            ret = "Blaze";
        }
        if (nome.equalsIgnoreCase("CaveSpider")) {
            ret = "Aranha ven.";
        }
        if (nome.equalsIgnoreCase("PigZombie")) {
            ret = "Porco Zumbi";
        }
        if (nome.equalsIgnoreCase("VillagerGolem")) {
            ret = "Golem de Ferro";
        }
        if (nome.equalsIgnoreCase("Pig")) {
            ret = "Porco";
        }
        if (nome.equalsIgnoreCase("Sheep")) {
            ret = "Ovelha";
        }
        if (nome.equalsIgnoreCase("Zombie")) {
            ret = "Zumbi";
        }
        if (nome.equalsIgnoreCase("Cow")) {
            ret = "Vaca";
        }
        if (nome.equalsIgnoreCase("Skeleton")) {
            ret = "Esqueleto";
        }
        if (nome.equalsIgnoreCase("Slime")) {
            ret = "Slime";
        }
        if (nome.equalsIgnoreCase("Mooshroom")) {
            ret = "Vaca Cogu.";
        }
        if (nome.equalsIgnoreCase("Enderman")) {
            ret = "Enderman";
        }
        if (nome.equalsIgnoreCase("Chicken")) {
            ret = "Galinha";
        }
        if (nome.equalsIgnoreCase("Creeper")) {
            ret = "Creeper";
        }
        if (nome.equalsIgnoreCase("WitherBoss")) {
            ret = "Wither";
        }
        if (nome.equalsIgnoreCase("Witch")) {
            ret = "Bruxa";
        }
        if (nome.equalsIgnoreCase("LavaSlime")) {
            ret = "Magma";
        }
        if (ret.equals("")) {
            ret = nome;
        }
        return ret;
    }

    public static String head(EntityType t) {
        if (t == null) return "";
        switch (t) {
            case BLAZE:
                return "MHF_BLAZE";
            case CAVE_SPIDER:
                return "MHF_CAVESPIDER";
            case CHICKEN:
                return "MHF_CHICKEN";
            case COW:
                return "MHF_COW";
            case CREEPER: 		//
                return "MHF_CREEPER"; //
            case GHAST:
                return "MHF_GHAST";
            case IRON_GOLEM:
                return "MHF_GOLEM";
            case MAGMA_CUBE:
                return "MHF_LavaSlime";
            case OCELOT:
                return "MHF_OCELOT";
            case PIG:
                return "MHF_PIG";
            case PIG_ZOMBIE:
                return "MHF_PIGZOMBIE";
            case SHEEP:
                return "MHF_SHEEP";
            case SKELETON: 		//
                return "MHF_SKELETON"; //
            case SLIME:
                return "MHF_SLIME";
            case SPIDER:
                return "MHF_SPIDER";
            case SQUID:
                return "MHF_SQUID";
            case WITHER:
                return "MHF_WITHER";
            case ZOMBIE: 	///
                return "MHF_ZOMBIE"; //
            case ENDERMAN:
                return "MHF_ENDERMAN";
            case WOLF:
                return "MHF_WOLF";
            case GUARDIAN:
                return "MHF_GUARDIAN";
        }
        return "MHF_"+t.toString().toUpperCase();
    }
}
