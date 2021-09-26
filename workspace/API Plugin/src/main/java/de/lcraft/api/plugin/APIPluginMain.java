package de.lcraft.api.plugin;

import de.lcraft.api.plugin.modules.ModuleCommand;
import de.lcraft.api.plugin.modules.ModuleManager;
import de.lcraft.api.plugin.utils.Config;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class APIPluginMain extends JavaPlugin {

    private APIPluginMain apiPluginMain;
    private Config cfg;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        apiPluginMain = this;
        cfg = new Config("config.yml");
        moduleManager = new ModuleManager(apiPluginMain);
        moduleManager.loadModules();
        moduleManager.onLoadAllModules();

        moduleManager.onEnableAllModules();
    }

    public void registerModuleCommand(String command, ModuleCommand cmd) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("description", "");
        this.getDescription().getCommands().put(command, obj);
        getCommand(command).setExecutor(cmd);
    }

    @Override
    public void onDisable() {
        moduleManager.onDisableAllModules();
    }

    public APIPluginMain getApiPluginMain() {
        return apiPluginMain;
    }

}
