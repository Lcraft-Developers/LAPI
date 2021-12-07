package de.lcraft.api.utils.minecraft.bungeecord.module.player;

import de.lcraft.api.utils.minecraft.bungeecord.utils.listeners.ListenerManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class UserManager {

    public ArrayList<LcraftUser> users;
    private LcraftBungeecordUserListener userListener;

    public UserManager(ListenerManager listenerManager) {
        users = new ArrayList<>();

        userListener = new LcraftBungeecordUserListener(this);
        listenerManager.registerListener(userListener);
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
    public Player getUserPlayer(UUID player) {
        return Bukkit.getPlayer(player);
    }
    public ProxiedPlayer getUserProxiedPlayer(UUID player) {
        return ProxyServer.getInstance().getPlayer(player);
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
