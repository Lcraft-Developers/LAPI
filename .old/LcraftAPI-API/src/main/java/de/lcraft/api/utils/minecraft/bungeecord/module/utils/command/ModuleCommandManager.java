package de.lcraft.api.utils.minecraft.bungeecord.module.utils.command;

import de.lcraft.api.utils.minecraft.bungeecord.BungeeClass;
import de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.Language;
import de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.LanguagesManager;
import de.lcraft.api.utils.minecraft.bungeecord.module.Module;
import de.lcraft.api.utils.minecraft.bungeecord.permissions.PermsManager;
import de.lcraft.api.utils.minecraft.bungeecord.module.utils.configs.ModuleConfig;
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
                addCommand(executor, canDisableInConfig);
            }
        } else {
            ProxyServer.getInstance().getPluginManager().registerCommand(BungeeClass.getApiPluginMain(), executor);
            modulesCmds.add(executor);
        }
    }

    public void reloadConfigs() throws IOException {
        PermsManager manager = new PermsManager();
        if(getAllPermissions() != null) {
            for(String c : getAllPermissions()) {
                manager.logPermission(c);
            }
        }

        LanguagesManager languagesManager = new LanguagesManager();
        if(getAllTranslatedTexts() != null) {
            for(String c : getAllTranslatedTexts()) {
                for(Language lang : languagesManager.getLangs()) {
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
