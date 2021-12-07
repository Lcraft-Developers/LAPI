package de.lcraft.api.utils.minecraft.spigot.module.player;

import de.lcraft.api.utils.minecraft.spigot.utils.listeners.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class LcraftBukkitUserListener extends Listener {

    private UserManager manager;

    public LcraftBukkitUserListener(UserManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        getManager().addPlayer(e.getPlayer().getUniqueId());
    }
    @EventHandler
    public void onLeave(PlayerJoinEvent e) {
        getManager().removePlayer(e.getPlayer().getUniqueId());
    }

    public UserManager getManager() {
        return manager;
    }

}
