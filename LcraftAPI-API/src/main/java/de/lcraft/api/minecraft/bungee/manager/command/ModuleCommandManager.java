package de.lcraft.api.minecraft.bungee.manager.command;

import de.lcraft.api.minecraft.bungee.BungeeClass;
import de.lcraft.api.minecraft.bungee.manager.Module;
import de.lcraft.api.minecraft.bungee.manager.ModuleConfig;
import de.lcraft.api.minecraft.bungee.manager.utils.LanguagesManager;
import de.lcraft.api.minecraft.bungee.manager.utils.PermsManager;
import net.md_5.bungee.api.ProxyServer;
import java.io.IOException;
import java.util.ArrayList;

public class ModuleCommandManager {

    private Module module;
    private ArrayList<ModuleCommand> modulesCmds;
    private ModuleConfig moduleCommands;

    public ModuleCommandManager(Module module) throws IOException {
        this.module = module;
        modulesCmds = new ArrayList<>();
        moduleCommands = new ModuleConfig(module, "commands.yml");
    }

    public void addCommand(ModuleCommand executor, boolean canDisableInConfig) {
        if(canDisableInConfig) {
            if(moduleCommands.cfg().contains("commands." + executor.getName())) {
                if(moduleCommands.cfg().getBoolean("commands." + executor.getName())) {
                    addCommand(executor, false);
                }
            } else {
                moduleCommands.cfg().set("commands." + executor.getName(), true);
                addCommand(executor, true);
            }
        } else {
            ProxyServer.getInstance().getPluginManager().registerCommand(BungeeClass.getAPIPluginMain(), executor);
            modulesCmds.add(executor);
        }
    }
    public void reloadConfigs() throws IOException {
        PermsManager manager = module.getPermsManager();
        if(getAllPermissions() != null) {
            for(String c : getAllPermissions()) {
                manager.logPermission(c);
            }
        }

        LanguagesManager languagesManager = module.getLanguagesManager();
        if(getAllTranslatedTexts() != null) {
            for(String c : getAllTranslatedTexts()) {
                for(LanguagesManager.Language lang : languagesManager.getAllLanguagesAndAdded()) {
                    lang.translate(c);
                }
            }
        }
    }

    public ArrayList<String> getAllTranslatedTexts() {
        ArrayList<String> allTranslations = new ArrayList<>();
        for(ModuleCommand cmd : getModulesCmds()) {
            allTranslations = cmd.getAllTranslations(allTranslations);
        }
        return allTranslations;
    }
    public ArrayList<String> getAllPermissions() {
        ArrayList<String> allPermissions = new ArrayList<>();
        for(ModuleCommand cmd : getModulesCmds()) {
            allPermissions = cmd.getAllPermissions(allPermissions);
        }
        return allPermissions;
    }

    public ArrayList<ModuleCommand> getModulesCmds() {
        return modulesCmds;
    }

}
