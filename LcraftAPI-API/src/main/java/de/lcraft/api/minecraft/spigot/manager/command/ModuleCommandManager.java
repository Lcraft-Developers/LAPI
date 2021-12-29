package de.lcraft.api.minecraft.spigot.manager.command;

import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.ModuleConfig;
import de.lcraft.api.minecraft.spigot.manager.utils.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.PermsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import java.io.IOException;
import java.lang.reflect.Field;
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
