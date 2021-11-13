package de.lcraft.api.bungeecord.testplugin.listeners;

import com.destroystokyo.paper.event.player.PlayerConnectionCloseEvent;
import de.lcraft.api.utils.minecraft.bungeecord.module.utils.listeners.Listener;
import org.bukkit.event.EventHandler;

public class JoinListener extends Listener {

    @EventHandler
    public void onQuit(PlayerConnectionCloseEvent e) {
    }

}
