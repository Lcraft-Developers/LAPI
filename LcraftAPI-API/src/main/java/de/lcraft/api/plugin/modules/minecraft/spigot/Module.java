package de.lcraft.api.plugin.modules.minecraft.spigot;

import de.lcraft.api.plugin.modules.minecraft.spigot.logger.Logger;
import de.lcraft.api.plugin.modules.minecraft.spigot.commands.ModuleCommandManager;
import de.lcraft.api.plugin.modules.minecraft.spigot.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;

public abstract class Module {

    private String name,
                   version,
                   api_version,
                   description;
    private ArrayList<String> authors = new ArrayList<>();
    private Logger logger;
    private ModuleCommandManager moduleCommandManager;
    private JavaPlugin plugin;
    private ListenerManager listenerManager;

    public Module() {
        logger = new Logger(getName());
        moduleCommandManager = new ModuleCommandManager(this);
        listenerManager = new ListenerManager(this);
    }

    public abstract void onLoad();
    public abstract void onEnable();
    public abstract void onDisable();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Logger getLogger() {
        return logger;
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
    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }
    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getDescription() {
        return description;
    }
    public String getApi_version() {
        return api_version;
    }
    public String getVersion() {
        return version;
    }
    public ArrayList<String> getAuthors() {
        return authors;
    }

}
