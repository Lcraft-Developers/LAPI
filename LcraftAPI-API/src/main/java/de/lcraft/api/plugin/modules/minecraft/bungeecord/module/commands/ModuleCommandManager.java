package de.lcraft.api.plugin.modules.minecraft.bungeecord.module.commands;

import de.lcraft.api.plugin.main.bungeecord.APIPluginMain;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.Module;
import net.md_5.bungee.api.ProxyServer;

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
        ProxyServer.getInstance().getPluginManager().registerCommand(plugin, executor);
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
