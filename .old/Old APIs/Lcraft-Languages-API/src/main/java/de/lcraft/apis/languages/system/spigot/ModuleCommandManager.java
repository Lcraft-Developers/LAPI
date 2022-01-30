package de.lcraft.apis.languages.system.spigot;

import de.lcraft.apis.languages.utils.spigot.ModuleCommand;
import java.util.ArrayList;

public class ModuleCommandManager {

    private ArrayList<ModuleCommand> commands;
    private de.lcraft.api.plugin.utils.minecraft.spigot.module.commands.ModuleCommandManager commandManager;

    public ModuleCommandManager(de.lcraft.api.plugin.utils.minecraft.spigot.module.commands.ModuleCommandManager commandManager) {
        this.commandManager = commandManager;
        commands = new ArrayList<>();
    }

    public void addCommand(ModuleCommand executor) {
        commands.add(executor);
        commandManager.addCommand(executor);
    }



}
