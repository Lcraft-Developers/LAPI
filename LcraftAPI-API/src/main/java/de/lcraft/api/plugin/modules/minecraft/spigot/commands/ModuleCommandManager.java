package de.lcraft.api.plugin.modules.minecraft.spigot.commands;

import de.lcraft.api.plugin.main.spigot.APIPluginMain;
import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ModuleCommandManager {

    private Module module;
    private ArrayList<ModuleCommand> modulesCmds;

    public ModuleCommandManager(Module module) {
        this.module = module;
        modulesCmds = new ArrayList<>();
    }

    public void addCommand(String command, ModuleCommand executor) {
        APIPluginMain plugin = APIPluginMain.getApiPluginMain();
        /*Map<String, Map<String, Object>> commands = plugin.getDescription().getCommands();
        Map<String, Object> nested = new HashMap<>();
        if(usage != null) {
            nested.put("usage", usage);
        }
        if(description != null) {
            nested.put("description", description);
        }
        commands.put(command, nested);*/
        try {
            final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            final CommandMap commandMap = ((CommandMap) commandMapField.get(Bukkit.getServer()));
            commandMap.register(command, executor);
        } catch (final IllegalAccessException | NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        modulesCmds.add(executor);
    }

    /*public void addInvisibleCommand(String command, CommandExecutor executor) {
        APIPluginMain plugin = APIPluginMain.getApiPluginMain();
        Map<String, Map<String, Object>> commands = plugin.getDescription().getCommands();
        Map<String, Object> nested = new HashMap<>();
        if(usage != null) {
            nested.put("usage", usage);
        }
        if(description != null) {
            nested.put("description", description);
        }
        commands.put(command, nested);
        plugin.getCommand(command).setExecutor(executor);
    }*/
    public ArrayList<ModuleCommand> getModulesCmds() {
        return modulesCmds;
    }
}
