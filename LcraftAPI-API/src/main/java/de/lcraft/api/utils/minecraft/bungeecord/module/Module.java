package de.lcraft.api.utils.minecraft.bungeecord.module;

import de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.LanguagesManager;
import de.lcraft.api.utils.minecraft.bungeecord.manager.ModuleManager;
import de.lcraft.api.utils.minecraft.bungeecord.module.logger.ModuleLogger;
import de.lcraft.api.utils.minecraft.bungeecord.module.player.UserManager;
import de.lcraft.api.utils.minecraft.bungeecord.module.utils.command.ModuleCommandManager;
import de.lcraft.api.utils.minecraft.bungeecord.utils.listeners.ListenerManager;
import de.lcraft.api.utils.minecraft.bungeecord.permissions.PermsManager;
import de.lcraft.api.utils.minecraft.bungeecord.module.utils.configs.ModuleConfig;
import de.lcraft.api.utils.minecraft.bungeecord.module.utils.prefixhelper.PrefixHelper;
import de.lcraft.api.utils.minecraft.bungeecord.manager.ModuleEventManager;
import net.md_5.bungee.api.plugin.Plugin;
import java.io.File;
import java.io.IOException;

public abstract class Module {

    private ModuleDescriptionFile moduleDescriptionFile;
    private ModuleLogger moduleLogger;
    private ModuleCommandManager moduleCommandManager;
    private LanguagesManager languagesManager;
    private Plugin plugin;
    private ListenerManager listenerManager;
    private File file;
    private ModuleManager manager;
    private PermsManager permsManager;
    private PrefixHelper prefixHelper;
    private ModuleConfig config;
    private UserManager lcraftUserManager;

    public void load(ModuleManager manager) throws Exception {
        this.manager = manager;
        ModuleEventManager eventManager = new ModuleEventManager(this);

        moduleDescriptionFile = new ModuleDescriptionFile(file);
        moduleDescriptionFile.load();
        moduleLogger = new ModuleLogger(moduleDescriptionFile.getName());
        config = new ModuleConfig(getModuleDescriptionFile().getName(), "config.yml");

        permsManager = new PermsManager();
        languagesManager = new LanguagesManager();
        moduleCommandManager = new ModuleCommandManager(this);
        listenerManager = new ListenerManager(this);
        lcraftUserManager = new UserManager(listenerManager);

        eventManager.loadModule();

        prefixHelper = new PrefixHelper();
        prefixHelper.startPlugin(config);

        eventManager.enableModule();

        listenerManager.registerAllListeners();
        moduleCommandManager.reloadConfigs();
    }

    public abstract void onLoad() throws IOException;
    public abstract void onEnable() throws IOException;
    public abstract void onDisable() throws IOException;

    public ModuleLogger getLogger() {
        return moduleLogger;
    }
    public ModuleCommandManager getModuleCommandManager() {
        return moduleCommandManager;
    }
    public Plugin getPlugin() {
        return plugin;
    }
    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public File getFile() {
        return file;
    }
    public ListenerManager getListenerManager() {
        return listenerManager;
    }
    public ModuleDescriptionFile getModuleDescriptionFile() {
        return moduleDescriptionFile;
    }
    public void setModuleDescriptionFile(ModuleDescriptionFile moduleDescriptionFile) {
        this.moduleDescriptionFile = moduleDescriptionFile;
    }
    public LanguagesManager getLanguagesManager() {
        return languagesManager;
    }
    public ModuleManager getModuleManager() {
        return manager;
    }
    public PermsManager getPermsManager() {
        return permsManager;
    }
    public PrefixHelper getPrefixHelper() {
        return prefixHelper;
    }
    public UserManager getUserManager() {
        return lcraftUserManager;
    }

}
