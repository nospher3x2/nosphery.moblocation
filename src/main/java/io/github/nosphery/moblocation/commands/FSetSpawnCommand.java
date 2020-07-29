package io.github.nosphery.moblocation.commands;

import io.github.nosphery.moblocation.mob.inventories.MobSelectInventory;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;

/**
 * @author oNospher
 **/
public class FSetSpawnCommand implements Listener {

    Rel[] rels = new Rel[]{
            Rel.OFFICER,
            Rel.LEADER
    };

    @EventHandler(ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if(event.getMessage().toLowerCase().startsWith("/f setspawn")) {
            if(event.isCancelled()) return;
            event.setCancelled(true);
            Player player = event.getPlayer();
            MPlayer mPlayer = MPlayer.get(player);
            if(!player.hasPermission("io.github.nosphery.moblocation.commands.setspawn")) {
                player.sendMessage("§cVocê não possui permissão para executar este comando.");
                return;
            }
            if (!mPlayer.hasFaction()) {
                player.sendMessage("§cVocê não possui uma facção.");
                return;
            }
            if(!Arrays.asList(rels).contains(mPlayer.getRole())) {
                player.sendMessage("§cVocê precisa ser capitão ou superior para executar este comando.");
                return;
            }
            Faction faction = BoardColl.get().getFactionAt(PS.valueOf(player.getLocation()));
            if(faction == null || faction != mPlayer.getFaction()) {
                player.sendMessage("§cVocê precisa estar dentro do território da sua facção para executar este comando.");
                return;
            }
            MobSelectInventory.getInventory(mPlayer).open(player);
        }
    }
}
