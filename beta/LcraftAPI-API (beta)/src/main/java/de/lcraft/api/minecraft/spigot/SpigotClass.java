package de.lcraft.api.minecraft.spigot;

import de.lcraft.api.java_utils.language.DefaultLanguages;
import de.lcraft.api.java_utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.plugin.commands.languagesCommands.LangCommand;
import de.lcraft.api.minecraft.spigot.plugin.commands.lcraftCommands.LcraftCommand;
import de.lcraft.api.minecraft.spigot.module.manager.ModuleManager;
import de.lcraft.api.minecraft.spigot.module.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.module.manager.command.CommandManager;
import de.lcraft.api.minecraft.spigot.module.manager.configs.BukkitConfig;
import de.lcraft.api.minecraft.spigot.module.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.module.player.LPlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class SpigotClass extends JavaPlugin {

    private static SpigotClass apiPluginMain;
    private BukkitConfig cfg,
                   userConfig;
    private ModuleManager moduleManager;
    private CommandManager commandManager;
    private LPlayerManager lPlayerManager;
    private StandardMessages standardMessages;
    private LanguagesManager languagesManager;
    private PermsManager permsManager;
    private ListenerManager listenerManager;

    @Override
    public final void onEnable() {
        try {
            // Initialize static classes
            new DefaultLanguages();

            // Make Variables
            apiPluginMain = this;
            cfg = new BukkitConfig("", "config.yml");
            userConfig = new BukkitConfig("users.yml");
            standardMessages = new StandardMessages(new BukkitConfig("standardMessages.yml"));
            languagesManager = new LanguagesManager();
            permsManager = new PermsManager("*", "admin");
            commandManager = new CommandManager(getStandardMessages());
            lPlayerManager = new LPlayerManager(getAPIPluginMain(), getUserConfig(), getAPIPluginMain(), getLanguagesManager());
            lPlayerManager.reloadPlayers();
            listenerManager = new ListenerManager(getAPIPluginMain());
            getCommandManager().addCommand(new LangCommand(getStandardMessages(), getPermsManager(), getLPlayerManager(), getLanguagesManager()), true);
            getCommandManager().addCommand(new LcraftCommand(getStandardMessages(), getPermsManager(), getLPlayerManager(), getLanguagesManager()), false);
            getCommandManager().reloadConfigs(getPermsManager(), getLanguagesManager());

            // Load Modules
            moduleManager = new ModuleManager(apiPluginMain);
            moduleManager.loadAllModules();

            getLogger().log(Level.INFO, getStandardMessages().getON_START());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public final void onDisable() {
        moduleManager.onDisableAllModules();

        getLogger().log(Level.INFO, getStandardMessages().getON_STOP());
    }

    public static final SpigotClass getAPIPluginMain() {
        return apiPluginMain;
    }
    public final BukkitConfig getMainCfg() {
        return cfg;
    }
    public final BukkitConfig getUserConfig() {
        return userConfig;
    }
    public final LPlayerManager getLPlayerManager() {
        return lPlayerManager;
    }
    public final StandardMessages getStandardMessages() {
        return standardMessages;
    }
    public final ModuleManager getModuleManager() {
        return moduleManager;
    }
    private final LanguagesManager getLanguagesManager() {
        return languagesManager;
    }
    public final CommandManager getCommandManager() {
        return commandManager;
    }
    public final PermsManager getPermsManager() {
        return permsManager;
    }
    private final ListenerManager getListenerManager() {
        return listenerManager;
    }

}
