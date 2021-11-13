package de.lcraft.api.utils.minecraft.bungeecord.module.utils.command;

import de.lcraft.api.utils.minecraft.bungeecord.BungeeClass;
import de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.Language;
import de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.LanguagesManager;
import de.lcraft.api.utils.minecraft.bungeecord.module.Module;
import de.lcraft.api.utils.minecraft.bungeecord.permissions.PermsManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ModuleCommandManager {

    private Module module;
    private ArrayList<ModuleCommand> modulesCmds;

    public ModuleCommandManager(Module module) {
        this.module = module;
        modulesCmds = new ArrayList<>();
    }

    public void addCommand(ModuleCommand executor) {
        ProxyServer.getInstance().getPluginManager().registerCommand(BungeeClass.getApiPluginMain(), executor);
        modulesCmds.add(executor);
    }

    public void reloadConfigs() throws IOException {
        PermsManager manager = new PermsManager();
        for(String c : getAllPermissions()) {
            manager.logPermission(c);
        }

        LanguagesManager languagesManager = new LanguagesManager();
        for(String c : getAllTranslatedTexts()) {
            for(Language lang : languagesManager.getLangs()) {
                lang.translate(c);
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
