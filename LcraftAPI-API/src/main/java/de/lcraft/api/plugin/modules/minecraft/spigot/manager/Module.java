package de.lcraft.api.plugin.modules.minecraft.spigot.manager;

import de.lcraft.api.plugin.modules.minecraft.spigot.manager.logger.ModuleLogger;
import de.lcraft.api.plugin.modules.minecraft.spigot.module.commands.ModuleCommandManager;
import de.lcraft.api.plugin.modules.minecraft.spigot.module.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Module {

    private String name,
                   version,
                   description;
    private ArrayList<String> authors = new ArrayList<>();
    private ModuleLogger moduleLogger;
    private ModuleCommandManager moduleCommandManager;
    private JavaPlugin plugin;
    private ListenerManager listenerManager;
    private List<Module> requiredModules;
    private File mainFile;

    public Module(Module... requiredModules) {
        moduleLogger = new ModuleLogger(getName());
        moduleCommandManager = new ModuleCommandManager(this);
        listenerManager = new ListenerManager(this);

        this.requiredModules = Arrays.stream(requiredModules).toList();
    }

    public abstract void onLoad() throws IOException;
    public abstract void onEnable() throws IOException;
    public abstract void onDisable() throws IOException;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
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
    public String getVersion() {
        return version;
    }
    public ArrayList<String> getAuthors() {
        return authors;
    }
    public List<Module> getRequiredModules() {
        return requiredModules;
    }
    public void setMainFile(File mainFile) {
        this.mainFile = mainFile;
    }
    public File getMainFile() {
        return mainFile;
    }

}
