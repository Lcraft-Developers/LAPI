package de.lcraft.api.utils.minecraft.spigot.module.player;

import de.lcraft.api.utils.minecraft.spigot.utils.listeners.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LcraftBukkitUserListener extends Listener {

    private UserManager manager;

    public LcraftBukkitUserListener(UserManager manager) {
        this.manager = manager;
    }

    public void onJoin(PlayerJoinEvent e) {
        getManager().addPlayer(e.getPlayer().getUniqueId());
    }
    public void onLeave(PlayerJoinEvent e) {
        getManager().addPlayer(e.getPlayer().getUniqueId());
    }

    public UserManager getManager() {
        return manager;
    }

}
