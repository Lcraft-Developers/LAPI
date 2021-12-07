package de.lcraft.api.utils.minecraft.spigot.module.player;

import de.lcraft.api.utils.minecraft.spigot.utils.listeners.ListenerManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class UserManager {

    public ArrayList<LcraftUser> users = new ArrayList<>();
    private LcraftBukkitUserListener userListener;

    public UserManager(ListenerManager listenerManager) {
        users = new ArrayList<>();

        userListener = new LcraftBukkitUserListener(this);
        listenerManager.registerListener(userListener);
    }

    public Player getUserPlayer(UUID player) {
        return Bukkit.getPlayer(player);
    }
    public ProxiedPlayer getUserProxiedPlayer(UUID player) {
        return ProxyServer.getInstance().getPlayer(player);
    }
    public LcraftUser addPlayer(UUID player) {
        LcraftUser user;
        if(!isPlayerExiting(player)) {
            user = new LcraftUser(player, this);
        } else {
            user = getPlayerByUUID(player);
        }
        users.add(user);
        return user;
    }
    public boolean removePlayer(UUID player) {
        if(!isPlayerExiting(player)) return false;
        users.remove(getPlayerByUUID(player));
        return true;
    }
    public LcraftUser getPlayerByUUID(UUID uuid) {
        for(LcraftUser c : users) {
            if(c.getUUID().equals(uuid)) {
                return c;
            }
        }
        return null;
    }
    public boolean isPlayerExiting(UUID player) {
        if(getPlayerByUUID(player) != null) {
            return true;
        }
        return false;
    }

}
