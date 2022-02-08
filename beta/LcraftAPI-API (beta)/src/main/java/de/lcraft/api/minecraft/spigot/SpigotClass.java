package de.lcraft.api.minecraft.spigot;

import de.lcraft.api.minecraft.spigot.commands.languagesCommands.LangCommand;
import de.lcraft.api.minecraft.spigot.commands.lcraftCommands.LcraftCommand;
import de.lcraft.api.minecraft.spigot.manager.ModuleManager;
import de.lcraft.api.minecraft.spigot.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.utils.command.CommandManager;
import de.lcraft.api.minecraft.spigot.utils.server.ServerTPS;
import de.lcraft.api.minecraft.spigot.manager.configs.BukkitConfig;
import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.LPlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotClass extends JavaPlugin {

    private static SpigotClass apiPluginMain;
    private BukkitConfig cfg,
                   userConfig;
    private ModuleManager moduleManager;
    private ServerTPS serverTPS;
    private ListenerManager listenerManager;
    private LanguagesManager languagesManager;
    private LPlayerManager lPlayerManager;
    private CommandManager commandManager;
    private PermsManager permsManager;

    @Override
    public final void onEnable() {
        try {
            apiPluginMain = this;
            cfg = new BukkitConfig("", "config.yml");
            userConfig = new BukkitConfig("users.yml");
            serverTPS = new ServerTPS(getAPIPluginMain());
            permsManager = new PermsManager("*", "admin");
            listenerManager = new ListenerManager(apiPluginMain);
            languagesManager = new LanguagesManager();
            lPlayerManager = new LPlayerManager(apiPluginMain, userConfig, getAPIPluginMain(), languagesManager);
            lPlayerManager.reloadPlayers();
            commandManager = new CommandManager();
            commandManager.addCommand(new LangCommand(getPermsManager(), getLPlayerManager(), getLanguagesManager(), getListenerManager()), true);
            commandManager.addCommand(new LcraftCommand(getPermsManager(), getLPlayerManager(), getLanguagesManager(), getListenerManager()), false);
            commandManager.reloadConfigs(getPermsManager(), getLanguagesManager());

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

    public static final SpigotClass getAPIPluginMain() {
        return apiPluginMain;
    }
    public final BukkitConfig getMainCfg() {
        return cfg;
    }
    public final ServerTPS getServerTPS() {
        return serverTPS;
    }
    public final BukkitConfig getUserConfig() {
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
    public PermsManager getPermsManager() {
        return permsManager;
    }

}
