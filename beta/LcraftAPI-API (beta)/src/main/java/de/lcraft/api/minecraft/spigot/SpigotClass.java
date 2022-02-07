package de.lcraft.api.minecraft.spigot;

import de.lcraft.api.minecraft.spigot.manager.ModuleManager;
import de.lcraft.api.minecraft.spigot.utils.server.ServerTPS;
import de.lcraft.api.minecraft.spigot.manager.configs.BukkitConfig;
import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.LPlayerManager;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotClass extends JavaPlugin {

    @Getter
    private SpigotClass apiPluginMain;
    @Getter (AccessLevel.PRIVATE)
    private BukkitConfig cfg,
                   userConfig;
    @Getter
    private ModuleManager moduleManager;
    @Getter
    private ServerTPS serverTPS;
    @Getter
    private ListenerManager listenerManager;
    @Getter
    private LanguagesManager languagesManager;
    @Getter
    private LPlayerManager lPlayerManager;

    @Override
    public final void onEnable() {
        try {
            apiPluginMain = this;
            cfg = new BukkitConfig("", "config.yml");
            userConfig = new BukkitConfig("users.yml");
            serverTPS = new ServerTPS(getApiPluginMain());
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

}
