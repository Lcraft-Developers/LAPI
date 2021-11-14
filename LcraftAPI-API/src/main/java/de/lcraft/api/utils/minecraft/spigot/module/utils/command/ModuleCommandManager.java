package de.lcraft.api.utils.minecraft.spigot.module.utils.command;

import de.lcraft.api.utils.minecraft.spigot.languages.filesystem.Language;
import de.lcraft.api.utils.minecraft.spigot.languages.filesystem.LanguagesManager;
import de.lcraft.api.utils.minecraft.spigot.module.Module;
import de.lcraft.api.utils.minecraft.spigot.permissions.PermsManager;
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
        try {
            final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            final CommandMap commandMap = ((CommandMap) commandMapField.get(Bukkit.getServer()));
            commandMap.register(executor.getName(), executor);
        } catch (final IllegalAccessException | NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        modulesCmds.add(executor);
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
