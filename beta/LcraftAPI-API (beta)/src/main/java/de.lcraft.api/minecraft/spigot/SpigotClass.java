package de.lcraft.api.minecraft.spigot;

import de.lcraft.api.minecraft.spigot.manager.configs.Config;
import de.lcraft.api.minecraft.spigot.manager.ModuleManager;
import de.lcraft.api.minecraft.spigot.manager.util.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.player.LPlayerManager;
import de.lcraft.api.minecraft.spigot.util.server.ServerTPS;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;

public class SpigotClass extends JavaPlugin {

    private SpigotClass apiPluginMain;
    private Config cfg,
                   userConfig;
    private ModuleManager moduleManager;
    private ServerTPS serverTPS;
    private ListenerManager listenerManager;
    private LanguagesManager languagesManager;
    private LPlayerManager lPlayerManager;

    @Override
    public void onEnable() {
        try {
            apiPluginMain = this;
            cfg = new Config("", "config.yml");
            userConfig = new Config("users.yml");
            serverTPS = new ServerTPS(getAPIPluginMain());
            listenerManager = new ListenerManager(apiPluginMain);
            languagesManager = new LanguagesManager();
            lPlayerManager = new LPlayerManager(apiPluginMain, userConfig, listenerManager, languagesManager);
            lPlayerManager.reloadPlayers();

            moduleManager = new ModuleManager(apiPluginMain);
            moduleManager.loadAllModules();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDisable() {
        lPlayerManager.savePlayers();

        moduleManager.onDisableAllModules();
    }

    public SpigotClass getAPIPluginMain() {
        return apiPluginMain;
    }
    public Config getMainCfg() {
        return cfg;
    }
    public ServerTPS getServerTPS() {
        return serverTPS;
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
    public LPlayerManager getLPlayerManager() {
        return lPlayerManager;
    }

}
