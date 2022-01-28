package de.lcraft.api.minecraft.bungee;

import de.lcraft.api.minecraft.bungee.manager.utils.Config;
import de.lcraft.api.minecraft.bungee.manager.ModuleManager;
import de.lcraft.api.minecraft.bungee.manager.utils.LPlayer;
import de.lcraft.api.minecraft.bungee.manager.utils.LanguagesManager;
import de.lcraft.api.minecraft.bungee.manager.utils.ListenerManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class BungeeClass extends Plugin implements Listener {

    private static BungeeClass apiPluginMain;
    private Config cfg,
                   userConfig;
    private ModuleManager moduleManager;
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
        if(userConfig.cfg().getSection("user") != null) {
            for(String c : userConfig.cfg().getSection("user").getKeys()) {
                UUID uuid = UUID.fromString(c);
                LPlayer p = new LPlayer(uuid, userConfig, getLanguagesManager(), getListenerManager(), getAPIPluginMain());
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
    public void onJoin(PlayerHandshakeEvent e) throws IOException {
        if(getLPlayerByUUID(e.getConnection().getUniqueId()) == null) {
            LPlayer p = new LPlayer(e.getConnection().getUniqueId(), userConfig, languagesManager, getListenerManager(), apiPluginMain);
            players.add(p);
        }
    }

    public LPlayer getLPlayerByPlayer(ProxiedPlayer p) {
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

    public static BungeeClass getAPIPluginMain() {
        return apiPluginMain;
    }
    public Config getMainCfg() {
        return cfg;
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
