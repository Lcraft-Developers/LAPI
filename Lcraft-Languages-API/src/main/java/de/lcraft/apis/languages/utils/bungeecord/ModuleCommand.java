package de.lcraft.apis.languages.utils.bungeecord;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.Module;
import de.lcraft.apis.languages.main.bungeecord.ModuleMain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public abstract class ModuleCommand extends de.lcraft.api.plugin.modules.minecraft.bungeecord.commands.ModuleCommand {

    private Module m;

    public ModuleCommand(String command, Module m) {
        super(command, m);
        this.m = m;
    }

    public String translate(String translate, UUID player) throws IOException {
        return ModuleMain.getLanguagesManager().getPlayer(player).translate(translate);
    }

    public abstract ArrayList<String> allPermissions(ArrayList<String> beforeAllPermissions);

}
