package de.lcraft.api.utils.minecraft.bungeecord.module.player;

import de.lcraft.api.utils.minecraft.bungeecord.utils.listeners.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LcraftBungeecordUserListener extends Listener {

    private UserManager manager;

    public LcraftBungeecordUserListener(UserManager manager) {
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
