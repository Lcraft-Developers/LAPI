package de.lcraft.api.minecraft.spigot.module.manager;

import de.lcraft.api.java_utils.connection.SpigotMc;
import de.lcraft.api.java_utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.module.manager.utils.command.ModuleCommandManager;
import de.lcraft.api.minecraft.spigot.module.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.module.manager.utils.listeners.ModuleListenerManager;
import de.lcraft.api.minecraft.spigot.module.manager.logger.ModuleLogger;
import de.lcraft.api.minecraft.spigot.module.manager.logger.ModuleLoggerType;
import de.lcraft.api.minecraft.spigot.module.manager.command.CommandManager;
import de.lcraft.api.minecraft.spigot.module.manager.configs.ModuleBukkitConfig;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.module.player.LPlayerManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.Objects;

public abstract class Module {

    private ModuleDescriptionFileManager moduleDescriptionFileManager;
    private ModuleLogger moduleLogger;
    private ModuleCommandManager moduleCommandManager;
    private LanguagesManager languagesManager;
    private JavaPlugin plugin;
    private ModuleListenerManager listenerManager;
    private File file;
    private ModuleManager manager;
    private PermsManager permsManager;
    private ModuleBukkitConfig config;
    private LPlayerManager lPlayerManager;
    private StandardMessages standardMessages;

    public final void load(ModuleManager manager) {
        this.manager = manager;
        this.lPlayerManager = manager.getPluginMain().getLPlayerManager();

        moduleDescriptionFileManager = new ModuleDescriptionFileManager(file);
        moduleDescriptionFileManager.load();
        moduleLogger = new ModuleLogger(moduleDescriptionFileManager.getName());
        config = new ModuleBukkitConfig(getModuleDescriptionFile().getName(), "config.yml");
        standardMessages = new StandardMessages(this);

        permsManager = new PermsManager();
        languagesManager = new LanguagesManager();
        moduleCommandManager = new ModuleCommandManager(getStandardMessages(), this);
        listenerManager = new ModuleListenerManager(this);

        enableModule();

        moduleCommandManager.reloadConfigs();

        sendUpdateMessageModule();
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public final void enableModule() {
        getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + getModuleDescriptionFile().getName() + " is loaded.");
        onEnable();
    }
    public void sendUpdateMessageModule() {
        if(Objects.nonNull(getModuleDescriptionFile().getUpdate_url())) {
            if(new SpigotMc().isUpdated(getModuleDescriptionFile().getUpdate_url(), getModuleDescriptionFile().getVersion())) {
                getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + getModuleDescriptionFile().getName() + " is up to date.");
            } else {
                getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + getModuleDescriptionFile().getName() + " is outdated.");
            }
        } else if(Objects.nonNull(getModuleDescriptionFile().getSpigotmc_id())) {
            if(new SpigotMc().isUpdated(Integer.valueOf(getModuleDescriptionFile().getSpigotmc_id()), getModuleDescriptionFile().getVersion())) {
                getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + getModuleDescriptionFile().getName() + " is up to date.");
            } else {
                getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + getModuleDescriptionFile().getName() + " is outdated.");
            }
        }
    }
    public final void disableModule() {
        getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + getModuleDescriptionFile().getName() + " was disabled.");
        onDisable();
    }

    public final void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public final void setFile(File file) {
        this.file = file;
    }
    public final void setModuleDescriptionFile(ModuleDescriptionFileManager moduleDescriptionFileManager) {
        this.moduleDescriptionFileManager = moduleDescriptionFileManager;
    }

    public final File getFile() {
        return file;
    }
    public final ModuleListenerManager getListenerManager() {
        return listenerManager;
    }
    public final ModuleDescriptionFileManager getModuleDescriptionFile() {
        return moduleDescriptionFileManager;
    }
    public final String getName() {
        return getModuleDescriptionFile().getName();
    }
    public final LanguagesManager getLanguagesManager() {
        return languagesManager;
    }
    public final ModuleManager getModuleManager() {
        return manager;
    }
    public final PermsManager getPermsManager() {
        return permsManager;
    }
    public final LPlayerManager getlPlayerManager() {
        return lPlayerManager;
    }
    public final ModuleLogger getModuleLogger() {
        return moduleLogger;
    }
    public final ModuleManager getManager() {
        return manager;
    }
    public final ModuleLogger getLogger() {
        return moduleLogger;
    }
    public final CommandManager getModuleCommandManager() {
        return moduleCommandManager;
    }
    public final JavaPlugin getPlugin() {
        return plugin;
    }
    public final ModuleBukkitConfig getConfig() {
        return config;
    }
    public final StandardMessages getStandardMessages() {
        return standardMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Module module = (Module) o;

        return Objects.nonNull(getName()) ? getName().equals(module.getName()) : module.getName() == null;
    }
    @Override
    public int hashCode() {
        return Objects.nonNull(getName()) ? getName().hashCode() : 0;
    }

}
