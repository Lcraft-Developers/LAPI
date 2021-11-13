package de.lcraft.apis.languages.system.bungeecord;

import de.lcraft.apis.languages.utils.bungeecord.ModuleCommand;
import java.util.ArrayList;

public class ModuleCommandManager {

    private ArrayList<ModuleCommand> commands;
    private de.lcraft.api.plugin.utils.minecraft.bungeecord.module.commands.ModuleCommandManager commandManager;

    public ModuleCommandManager(de.lcraft.api.plugin.utils.minecraft.bungeecord.module.commands.ModuleCommandManager commandManager) {
        this.commandManager = commandManager;
        commands = new ArrayList<>();
    }

    public void addCommand(ModuleCommand executor) {
        commands.add(executor);
        commandManager.addCommand(executor);
    }

    public ArrayList<String> getAllTranslatedTexts() {
        ArrayList<String> allTranslations = new ArrayList<>();
        for(ModuleCommand cmd : commands) {
            allTranslations = cmd.allLanguages(allTranslations);
        }
        return allTranslations;
    }

}
