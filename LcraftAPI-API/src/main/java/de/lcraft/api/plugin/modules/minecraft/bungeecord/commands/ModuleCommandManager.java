package de.lcraft.api.plugin.modules.minecraft.bungeecord.commands;

import de.lcraft.api.plugin.main.bungeecord.APIPluginMain;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.Module;
import net.md_5.bungee.api.ProxyServer;

import java.lang.reflect.Field;

public class ModuleCommandManager {

    private Module module;

    public ModuleCommandManager(Module module) {
        this.module = module;
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
        ProxyServer.getInstance().getPluginManager().registerCommand(plugin, executor);
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

}
