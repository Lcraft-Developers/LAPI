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
    public final void onEnable() {
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
    public final void onDisable() {
        moduleManager.onDisableAllModules();
    }

    public final SpigotClass getAPIPluginMain() {
        return apiPluginMain;
    }
    public final Config getMainCfg() {
        return cfg;
    }
    public final ServerTPS getServerTPS() {
        return serverTPS;
    }
    public final Config getUserConfig() {
        return userConfig;
    }
    public final ListenerManager getListenerManager() {
        return listenerManager;
    }
    public final LanguagesManager getLanguagesManager() {
        return languagesManager;
    }
    public final LPlayerManager getLPlayerManager() {
        return lPlayerManager;
    }

}
