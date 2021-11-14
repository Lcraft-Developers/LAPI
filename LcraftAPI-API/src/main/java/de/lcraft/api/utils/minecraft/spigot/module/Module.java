package de.lcraft.api.utils.minecraft.spigot.module;

import de.lcraft.api.utils.minecraft.spigot.SpigotClass;
import de.lcraft.api.utils.minecraft.spigot.languages.filesystem.LanguagesManager;
import de.lcraft.api.utils.minecraft.spigot.manager.ModuleEventManager;
import de.lcraft.api.utils.minecraft.spigot.manager.ModuleManager;
import de.lcraft.api.utils.minecraft.spigot.module.logger.ModuleLogger;
import de.lcraft.api.utils.minecraft.spigot.module.utils.command.ModuleCommandManager;
import de.lcraft.api.utils.minecraft.spigot.module.utils.configs.ModuleConfig;
import de.lcraft.api.utils.minecraft.spigot.module.utils.inventory.InventoryManager;
import de.lcraft.api.utils.minecraft.spigot.module.utils.prefixhelper.PrefixHelper;
import de.lcraft.api.utils.minecraft.spigot.permissions.PermsManager;
import de.lcraft.api.utils.minecraft.spigot.module.utils.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public abstract class Module {

    private ModuleDescriptionFile moduleDescriptionFile;
    private ModuleLogger moduleLogger;
    private ModuleCommandManager moduleCommandManager;
    private LanguagesManager languagesManager;
    private JavaPlugin plugin;
    private ListenerManager listenerManager;
    private File file;
    private ModuleManager manager;
    private PermsManager permsManager;
    private InventoryManager inventoryManager;
    private PrefixHelper prefixHelper;
    private ModuleConfig config;

    public void load(ModuleManager manager) throws Exception {
        ModuleEventManager eventManager = new ModuleEventManager(this);
        this.manager = manager;

        moduleDescriptionFile = new ModuleDescriptionFile(file);
        moduleDescriptionFile.load();
        moduleLogger = new ModuleLogger(moduleDescriptionFile.getName());
        config = new ModuleConfig(getModuleDescriptionFile().getName(), "config.yml");

        eventManager.loadModule();

        permsManager = new PermsManager();
        languagesManager = new LanguagesManager();
        moduleCommandManager = new ModuleCommandManager(this);
        listenerManager = new ListenerManager(this);

        inventoryManager = new InventoryManager(SpigotClass.getApiPluginMain());
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
    public JavaPlugin getPlugin() {
        return plugin;
    }
    public void setPlugin(JavaPlugin plugin) {
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
    public InventoryManager getInventoryManager() {
        return inventoryManager;
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

}
