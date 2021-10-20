package de.lcraft.api.plugin.modules.minecraft.bungeecord.commands;

import de.lcraft.api.plugin.main.bungeecord.APIPluginMain;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.Module;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.lang.reflect.Field;

public class ModuleCommandManager {

    private Module module;

    public ModuleCommandManager(Module module) {
        this.module = module;
    }

    public void addCommand(String command, ModuleCommand executor) throws NoSuchFieldException {
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
        final Field commandMapField = ProxyServer.getInstance().getClass().getDeclaredField("commandMap");
        commandMapField.setAccessible(true);

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
