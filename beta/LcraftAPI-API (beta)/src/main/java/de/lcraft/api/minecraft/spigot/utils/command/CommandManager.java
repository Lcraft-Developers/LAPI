package de.lcraft.api.minecraft.spigot.utils.command;

import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.configs.ModuleBukkitConfig;
import de.lcraft.api.minecraft.spigot.manager.utils.language.Language;
import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.permissions.PermsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

public class CommandManager {

    private Module module;
    private ArrayList<Command> modulesCmds;
    private ModuleBukkitConfig moduleCommands;

    public CommandManager(Module module) {
        this.module = module;
        modulesCmds = new ArrayList<>();
        moduleCommands = new ModuleBukkitConfig(module, "commands.yml");
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
                for(Language lang : languagesManager.getAllLanguagesAndAdded()) {
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
