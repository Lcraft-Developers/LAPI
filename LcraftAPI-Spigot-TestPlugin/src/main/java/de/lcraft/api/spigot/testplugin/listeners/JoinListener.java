package de.lcraft.api.spigot.testplugin.listeners;

import de.lcraft.api.utils.minecraft.spigot.utils.listeners.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener extends Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage("Hello");
    }

}
