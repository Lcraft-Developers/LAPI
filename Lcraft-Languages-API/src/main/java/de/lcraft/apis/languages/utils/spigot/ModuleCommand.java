package de.lcraft.apis.languages.utils.spigot;

import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import de.lcraft.apis.languages.main.spigot.ModuleMain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public abstract class ModuleCommand extends de.lcraft.api.plugin.modules.minecraft.spigot.commands.ModuleCommand {

    private Module m;

    public ModuleCommand(String command, String desc, String usage, Module m) {
        super(command, desc, usage, m);
        this.m = m;
    }

    public String translate(String translate, UUID player) throws IOException {
        return ModuleMain.getLanguagesManager().getPlayer(player).translate(translate);
    }

    public abstract ArrayList<String> allLanguages(ArrayList<String> beforeAllLanguages);

}
