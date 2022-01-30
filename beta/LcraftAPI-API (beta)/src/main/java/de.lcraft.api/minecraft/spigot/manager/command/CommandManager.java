package de.lcraft.api.minecraft.spigot.manager.command;

import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.configs.ModuleConfig;
import de.lcraft.api.minecraft.spigot.manager.util.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.util.PermsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

public class CommandManager {

    private Module module;
    private ArrayList<Command> modulesCmds;
    private ModuleConfig moduleCommands;

    public CommandManager(Module module) {
        this.module = module;
        modulesCmds = new ArrayList<>();
        moduleCommands = new ModuleConfig(module, "commands.yml");
    }

    public final void addCommand(Command executor, boolean canDisableInConfig) {
        if(canDisableInConfig) {
            if(moduleCommands.exists("commands." + executor.getName())) {
                if(moduleCommands.getBoolean("commands." + executor.getName())) {
                    addCommand(executor, false);
                }
            } else {
                moduleCommands.set("commands." + executor.getName(), true);
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
    public final void reloadConfigs() {
        PermsManager manager = module.getPermsManager();
        if(Objects.nonNull(getAllPermissions()) && !getAllPermissions().isEmpty()) {
            for(String c : getAllPermissions()) {
                manager.logPermission(c);
            }
        }

        LanguagesManager languagesManager = module.getLanguagesManager();
        if(Objects.nonNull(getAllTranslatedTexts()) && !getAllTranslatedTexts().isEmpty()) {
            for(String c : getAllTranslatedTexts()) {
                for(LanguagesManager.Language lang : languagesManager.getAllLanguagesAndAdded()) {
                    lang.translate(c);
                }
            }
        }
    }

    public final ArrayList<String> getAllTranslatedTexts() {
        ArrayList<String> allTranslations = new ArrayList<>();
        for(Command cmd : getModulesCmds()) {
            allTranslations = cmd.getAllTranslations(allTranslations);
        }
        return allTranslations;
    }
    public final ArrayList<String> getAllPermissions() {
        ArrayList<String> allPermissions = new ArrayList<>();
        for(Command cmd : getModulesCmds()) {
            allPermissions = cmd.getAllPermissions(allPermissions);
        }
        return allPermissions;
    }
    public final ArrayList<Command> getModulesCmds() {
        return modulesCmds;
    }

}
