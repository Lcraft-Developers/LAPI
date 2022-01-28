package de.lcraft.api.minecraft.spigot;

import de.lcraft.api.minecraft.spigot.manager.utils.Config;
import de.lcraft.api.minecraft.spigot.manager.ModuleManager;
import de.lcraft.api.minecraft.spigot.manager.utils.LPlayer;
import de.lcraft.api.minecraft.spigot.manager.utils.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.ListenerManager;
import de.lcraft.api.minecraft.spigot.utils.server.ServerTPS;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class SpigotClass extends JavaPlugin implements Listener {

    private static SpigotClass apiPluginMain;
    private Config cfg,
                   userConfig;
    private ModuleManager moduleManager;
    private ServerTPS serverTPS;
    private ArrayList<LPlayer> players;
    private ListenerManager listenerManager;
    private LanguagesManager languagesManager;

    @Override
    public void onEnable() {
        try {
            apiPluginMain = this;
            players = new ArrayList<>();
            cfg = new Config("", "config.yml");
            userConfig = new Config("users.yml");
            serverTPS = new ServerTPS(getAPIPluginMain());
            listenerManager = new ListenerManager(apiPluginMain);
            languagesManager = new LanguagesManager();
            listenerManager.registerListener(this);
            reloadPlayers();

            moduleManager = new ModuleManager(apiPluginMain);
            moduleManager.loadAllModules();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDisable() {
        try {
            savePlayers();
            moduleManager.onDisableAllModules();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadPlayers() throws IOException {
        if(userConfig.cfg().getConfigurationSection("user") != null) {
            for(String c : userConfig.cfg().getConfigurationSection("user").getKeys(false)) {
                UUID uuid = UUID.fromString(c);
                LPlayer p = new LPlayer(uuid, userConfig, getListenerManager(), getLanguagesManager(), getAPIPluginMain());
                players.add(p);
            }
        }
    }
    public void savePlayers() throws IOException {
        if(!players.isEmpty()) {
            for(LPlayer c : players) {
                c.setToConfig();
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) throws IOException {
        if(getLPlayerByUUID(e.getPlayer().getUniqueId()) == null) {
            LPlayer p = new LPlayer(e.getPlayer().getUniqueId(), userConfig, listenerManager, languagesManager, apiPluginMain);
            players.add(p);
        }
    }

    public LPlayer getLPlayerByPlayer(Player p) {
        return getLPlayerByUUID(p.getUniqueId());
    }
    public LPlayer getLPlayerByRealName(String realName) {
        for(LPlayer c : players) {
            if(c.getRealName().equalsIgnoreCase(realName)) {
                return c;
            }
        }
        return null;
    }
    public LPlayer getLPlayerByUUID(UUID uuid) {
        for(LPlayer c : players) {
            if(c.getUUID().equals(uuid)) {
                return c;
            }
        }
        return null;
    }
    public boolean existsPlayer(UUID uuid) {
        if(getLPlayerByUUID(uuid) != null) return true;
        return false;
    }

    public static SpigotClass getAPIPluginMain() {
        return apiPluginMain;
    }
    public Config getMainCfg() {
        return cfg;
    }
    public ServerTPS getServerTPS() {
        return serverTPS;
    }
    public ArrayList<LPlayer> getPlayers() {
        return players;
    }
    public Config getUserConfig() {
        return userConfig;
    }
    public ListenerManager getListenerManager() {
        return listenerManager;
    }
    public LanguagesManager getLanguagesManager() {
        return languagesManager;
    }

}
